package com.trainings.javafx;

import com.trainings.javafx.controllers.MainController;
import com.trainings.javafx.controllers.UsersTabPaneController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));
		VBox mainStage = loader.load();

		loader.setControllerFactory(initControllerFactory());

		primaryStage.setTitle("JPROG2");
		Scene scene = new Scene(mainStage);
		String modenaCss = getClass().getResource("/css/modena.css").toExternalForm();
		scene.getStylesheets().add(modenaCss);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private Callback<Class<?>, Object> initControllerFactory() {
		return type -> {
			if (type == App.class) {
				return new MainController();
			} else if (type == UsersTabPaneController.class) {
				return new UsersTabPaneController();
			} else {
				try {
					return type.newInstance();
				} catch (Exception exc) {
					exc.printStackTrace();
					throw new RuntimeException(exc); // fatal, just bail...
				}
			}
		};
	}

}
