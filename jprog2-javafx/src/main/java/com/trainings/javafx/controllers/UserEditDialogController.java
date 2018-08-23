package com.trainings.javafx.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.trainings.javafx.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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

	}

	public void initializeFieldsWithOldValues(User user) {
//		this.emailTextField.setText(user.getEmail());
//		this.nickNameTextField.setText(user.getNickname());
//		this.firstNameTextField.setText(user.getFirstName());
//		this.surnameTextField.setText(user.getSurname());
//		this.birthdayDatePicker.setValue(user.getBirthday());
	}

}
