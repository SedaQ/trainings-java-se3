package com.trainings.javafx.controllers;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import com.trainings.javafx.model.User;
import com.trainings.javafx.model.observablelists.UsersList;

import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class UsersTabPaneController {

	@FXML
	private TextField filterUsers;
	@FXML
	private ProgressIndicator progressIndicator;
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
	private UsersList usersList;

	/**
	 * The constructor. The constructor is called before the initialize() method.
	 */
	public UsersTabPaneController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		initializeTableColumns();

		usersList = new UsersList();
		usersTableView.setItems(usersList.getMockedListOfUsers());

		initializeTextFieldsListeners();
		initializeProgressBars();
		initializeTableViewListeners();
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
//		Task<Void> task = new Task<>() {
//			@Override
//			public Void call() {
//				int max = 1_000_000;
//				for (int i = 1; i <= max; i++) {
//					updateProgress(i, max);
//				}
//				return null;
//			}
//		};
//		progressIndicator.progressProperty().bind(task.progressProperty());
	}

	private void initializeTableViewListeners() {
		usersTableView.setRowFactory(tv -> {
			TableRow<User> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					try {
						User rowData = row.getItem();

						FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserEdit.fxml"));
						Parent root = fxmlLoader.load();
						Stage stage = new Stage();
						stage.setTitle("JProg2 Edit User");
						stage.setScene(new Scene(root));

						System.out.println(rowData);

						UserEditDialogController userEditController = fxmlLoader
								.<UserEditDialogController>getController();
						if (userEditController == null) {
							System.out.println("WHY THE HELL IS THIS NULL.." + System.lineSeparator());
						}
						userEditController.initializeFieldsWithOldValues(rowData);
						stage.show();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			});
			return row;
		});
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
			// TODO Auto-generated catch block
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
