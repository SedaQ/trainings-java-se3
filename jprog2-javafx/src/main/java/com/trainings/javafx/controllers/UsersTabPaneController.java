package com.trainings.javafx.controllers;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.trainings.javafx.model.User;
import com.trainings.javafx.model.observablelists.UsersList;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class UsersTabPaneController {

	@FXML
	private TextField filterUsers;
	@FXML
	private ProgressBar refreshTableProgressBar;
	@FXML
	private VBox usersTabPane;
	@FXML
	private TableView<User> usersTableView;
	@FXML
	private TableColumn<User, ImageView> userImageCol;
	@FXML
	private TableColumn<User, Long> userIdCol;
	@FXML
	private TableColumn<User, String> userEmailCol;
	@FXML
	private TableColumn<User, String> userNicknameCol;
	@FXML
	private TableColumn<User, String> userFirstNameCol;
	@FXML
	private TableColumn<User, String> userSurnameCol;
	@FXML
	private TableColumn<User, LocalDate> userBirthdayCol;
	@FXML
	private TableColumn<User, Integer> userAgeCol;
	@FXML
	private TableColumn<User, Integer> idAddress;
	@FXML
	private Button showAgeDistributionButton;
	@FXML
	private Label timerLabel;
	private UsersList usersList;
	private long refreshRate;
	private boolean dataRefreshing;
	private Task<Void> progressIndicatorTask;
	@FXML
	private ProgressIndicator refreshDataProgressIndicator;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public UsersTabPaneController() {
		refreshRate = 1_000_000L;
		dataRefreshing = false;
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		initializeTableColumns();

		refreshDataProgressIndicator.setVisible(false);

		usersList = new UsersList();
		usersTableView.setItems(usersList.getMockedListOfUsers());

		initializeTextFieldsListeners();
		initializeProgressBars();
		initializeTableViewListeners();

		timeLine();
	}

	private void initializeTableColumns() {
		userImageCol.setCellValueFactory(new PropertyValueFactory<>("image"));
		userIdCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
		userEmailCol.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
		userNicknameCol.setCellValueFactory(cellData -> cellData.getValue().nicknameProperty());
		userFirstNameCol.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		userSurnameCol.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
		userBirthdayCol.setCellValueFactory(cellData -> cellData.getValue().birthdayProperty());
		userAgeCol.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());
		idAddress.setCellValueFactory(cellData -> cellData.getValue().idAddressProperty().asObject());
	}

	private void initializeProgressBars() {
		progressIndicatorTask = new Task<>() {
			@Override
			public Void call() {
				for (int i = 1; i <= refreshRate; i++) {
					updateProgress(i, refreshRate);
					try {
						Thread.sleep(10L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				return null;
			}
		};
		refreshDataProgressIndicator.progressProperty().bind(progressIndicatorTask.progressProperty());
//		 refreshTableProgressBar.setProgress(progressBarTask.getProgress());
	}

	private void timeLine() {
		IntegerProperty seconds = new SimpleIntegerProperty();
		refreshTableProgressBar.progressProperty().bind(seconds.divide(10.0));
		Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(seconds, 0)),
				new KeyFrame(Duration.seconds(10), e -> {
				}, new KeyValue(seconds, 10)));
		timeline.setOnFinished(event -> {
			Task<Void> refreshData = new Task<>() {
				@Override
				public Void call() {
					System.out.println("On finished in Task");
					timeline.stop();
					refreshDataProgressIndicator.setVisible(true);
					usersTableView.setItems(usersList.getMockedListOfUsers());
					refreshDataProgressIndicator.setVisible(false);
					// refresh data in table..
					timeline.play();
					return null;
				}
			};
			Platform.runLater(refreshData);
		});
		timeline.setCycleCount(1);
		timeline.play();
	}

	private void initializeTableViewListeners() {
		usersTableView.setRowFactory(tv -> {
			TableRow<User> row = new TableRow<>();
			row.setOnMousePressed(event -> {
				if (event.isSecondaryButtonDown() && (!row.isEmpty())) {
					System.out.println("Second button is pressed...");
					final ContextMenu contextMenu = new ContextMenu();
					final MenuItem editUserItem = new MenuItem("Edit");
					final MenuItem viewUserItem = new MenuItem("View");

					editUserItem.setOnAction(ev -> {
						initializeUserEdit(row);
					});
					viewUserItem.setOnAction(ev -> {
						initializeUserView(row);
					});
					contextMenu.getItems().addAll(editUserItem, viewUserItem);
					// Set context menu on row, but use a binding to make it only show for non-empty
					// rows:
					row.contextMenuProperty()
							.bind(Bindings.when(row.emptyProperty()).then((ContextMenu) null).otherwise(contextMenu));
					contextMenu.show(row.getScene().getWindow());
				}
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					initializeUserEdit(row);
				}
			});
			return row;
		});
	}

	private void initializeUserView(TableRow<User> row) {
		try {
			User rowData = row.getItem();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserView.fxml"));
			Parent root = fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("JProg2 View User");
			stage.setScene(new Scene(root));

			UserViewDialogController userViewDialogController = fxmlLoader.<UserViewDialogController>getController();
			userViewDialogController.initializeFields(rowData);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initializeUserEdit(TableRow<User> row) {
		try {
			User rowData = row.getItem();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserEdit.fxml"));
			Parent root = fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("JProg2 Edit User");
			stage.setScene(new Scene(root));

			UserEditDialogController userEditController = fxmlLoader.<UserEditDialogController>getController();
			userEditController.initializeFieldsWithOldValues(rowData);

			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void handleShowAgeDistributionAction(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UsersAgeDistribution.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			// stage.initModality(Modality.APPLICATION_MODAL);
			// stage.initStyle(StageStyle.UNDECORATED);
			stage.setTitle("JProg2 Age Distribution");
			stage.setScene(new Scene(root));

			UsersAgeDistributionController usersAgeDistrib = fxmlLoader.<UsersAgeDistributionController>getController();

			usersAgeDistrib.initPieChartData(usersTableView.getItems());
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initializeTextFieldsListeners() {
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<User> filteredData = new FilteredList<>(usersTableView.getItems(), p -> true);

		// 2. Set the filter Predicate whenever the filter changes.
		filterUsers.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(user -> {
				// If filter text is empty, display all users.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				// Compare email, nickname, firstName, surname of every user with filter text
				String lowerCaseFilter = newValue.toLowerCase();
				if (user.getEmail().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (user.getNickname().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (user.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (user.getSurname().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				}
				try {
					int numberFilter = Integer.parseInt(lowerCaseFilter);
					if (user.getAge() == numberFilter) {
						return true;
					} else if (user.getId() == numberFilter) {
						return true;
					} else if (user.getIdAddress() == numberFilter) {
						return true;
					}
				} catch (NumberFormatException ex) {
					// log it
				}
				try {
					DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
					if (user.getBirthday().format(dateTimeFormatter).contains(lowerCaseFilter)) {
						return true;
					}
				} catch (DateTimeException ex) {
					// log it
				}
				return false; // Does not match.
			});
		});

		usersTableView.setItems(filteredData);
	}

}
