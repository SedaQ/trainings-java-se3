package com.trainings.javafx.controllers;

import com.trainings.javafx.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class UserEditDialogController {

	@FXML
	private TextField emailTextField;
	@FXML
	private TextField nickNameTextField;
	@FXML
	private TextField firstNameTextField;
	@FXML
	private TextField surnameTextField;
	@FXML
	private DatePicker birthdayDatePicker;
	@FXML
	private Button okButton;
	@FXML
	private Button cancelButton;

	public UserEditDialogController() {
	}

	@FXML
	private void initialize() {
	}

	@FXML
	void editUser(ActionEvent event) {
		// edit user in DB
	}

	public void initializeFieldsWithOldValues(User user) {
		this.emailTextField.setText(user.getEmail());
		this.nickNameTextField.setText(user.getNickname());
		this.firstNameTextField.setText(user.getFirstName());
		this.surnameTextField.setText(user.getSurname());
		this.birthdayDatePicker.setValue(user.getBirthday());
	}

	@FXML
	void handleOnCancelAction(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}

}
