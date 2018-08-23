package com.trainings.javafx.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;

/**
 * 
 * With JavaFX it's common to use Properties for all fields of a model class. A
 * Property allows us, for example, to automatically be notified when the
 * lastName or any other variable is changed. This helps us keep the view in
 * sync with the data. To learn more about Properties read Using JavaFX
 * Properties and Binding.
 * 
 * 
 * @author Pavel Šeda
 *
 */
public class User {

	private ObjectProperty<ImageView> image;
	private LongProperty id;
	private StringProperty email;
	private StringProperty nickname;
	private StringProperty firstName;
	private StringProperty surname;
	private ObjectProperty<LocalDate> birthday;
	private IntegerProperty age;
	private IntegerProperty idAddress;

	public User() {
	}

	public User(ImageView image, long id, String email, String firstName, String nickname, String surname,
			LocalDate birthday, int age, int idAddress) {
		super();
		this.image = new SimpleObjectProperty<ImageView>(image);
		this.id = new SimpleLongProperty(id);
		this.email = new SimpleStringProperty(email);
		this.firstName = new SimpleStringProperty(firstName);
		this.nickname = new SimpleStringProperty(nickname);
		this.surname = new SimpleStringProperty(surname);
		this.birthday = new SimpleObjectProperty<LocalDate>(birthday);
		this.age = new SimpleIntegerProperty(age);
		this.idAddress = new SimpleIntegerProperty(idAddress);
	}

	public ImageView getImage() {
		return image.get();
	}

	public void setImage(ImageView image) {
		this.image.set(image);
	}

	public ObjectProperty<ImageView> imageProperty() {
		return image;
	}

	public long getId() {
		return id.get();
	}

	public void setId(long id) {
		this.id.set(id);
	}

	public LongProperty idProperty() {
		return id;
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email) {
		this.email.set(email);
	}

	public StringProperty emailProperty() {
		return email;
	}

	public String getNickname() {
		return nickname.get();
	}

	public void setNickname(String nickname) {
		this.nickname.set(nickname);
	}

	public StringProperty nicknameProperty() {
		return nickname;
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public StringProperty firstNameProperty() {
		return firstName;
	}

	public String getSurname() {
		return surname.get();
	}

	public void setSurname(String surname) {
		this.surname.set(surname);
	}

	public StringProperty surnameProperty() {
		return surname;
	}

	public LocalDate getBirthday() {
		return birthday.get();
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday.set(birthday);
	}

	public ObjectProperty<LocalDate> birthdayProperty() {
		return birthday;
	}

	public int getAge() {
		return age.get();
	}

	public void setAge(int age) {
		this.age.set(age);
	}

	public IntegerProperty ageProperty() {
		return age;
	}

	public int getIdAddress() {
		return idAddress.get();
	}

	public void setIdAddress(int idAddress) {
		this.idAddress.set(idAddress);
	}

	public IntegerProperty idAddressProperty() {
		return idAddress;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", email=");
		builder.append(email);
		builder.append(", nickname=");
		builder.append(nickname);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", surname=");
		builder.append(surname);
		builder.append(", birthday=");
		builder.append(birthday);
		builder.append(", age=");
		builder.append(age);
		builder.append(", idAddress=");
		builder.append(idAddress);
		builder.append("]");
		return builder.toString();
	}

}
