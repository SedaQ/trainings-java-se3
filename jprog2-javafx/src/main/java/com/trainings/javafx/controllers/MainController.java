package com.trainings.javafx.controllers;

import com.trainings.javafx.App;

import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class MainController {

	@FXML
	private VBox users;
	@FXML
	private MenuBar menuBar;
	@FXML
	private VBox usersTabPane;
	@FXML
	private UsersTabPaneController usersTabPaneController;

	// Reference to the main application.
	private App mainApp;

	public MainController() {
//		usersTabPaneController.ini
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(App mainApp) {
		this.mainApp = mainApp;

	}

}
