package com.trainings.javafx.controllers;

import com.trainings.javafx.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class UserViewDialogController {

	@FXML
	private ImageView userImageView;
	@FXML
	private TextField emailTextField;
	@FXML
	private TextField nickNameTextField;
	@FXML
	private TextField firstNameTextField;
	@FXML
	private TextField surnameTextField;
	@FXML
	private Button cancelButton;
	@FXML
	private TextField addressIdTextField;
	@FXML
	private TextField birthdayTextField;

	@FXML
	private void initialize() {
		emailTextField.setEditable(false);
		nickNameTextField.setEditable(false);
		firstNameTextField.setEditable(false);
		surnameTextField.setEditable(false);
		addressIdTextField.setEditable(false);
		birthdayTextField.setEditable(false);
	}

	public void initializeFields(User user) {
		this.emailTextField.setText(user.getEmail());
		this.nickNameTextField.setText(user.getNickname());
		this.firstNameTextField.setText(user.getFirstName());
		this.surnameTextField.setText(user.getSurname());
		this.birthdayTextField.setText(user.getBirthday().toString());
		this.addressIdTextField.setText(String.valueOf(user.getIdAddress()));
		this.userImageView.setImage(user.imageProperty().get().getImage());
	}

	@FXML
	void handleOnCancelAction(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}

}
