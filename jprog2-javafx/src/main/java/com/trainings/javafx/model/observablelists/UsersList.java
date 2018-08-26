package com.trainings.javafx.model.observablelists;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;

import com.trainings.javafx.model.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class UsersList {

	private ObservableList<User> users = FXCollections.observableArrayList();

	public UsersList() {
	}

	public void setUsers(List<User> users) {
		this.users.addAll(users);
	}

	public ObservableList<User> getUsers() {
		return this.users;
	}

	public ObservableList<User> getMockedListOfUsers() {
		Image image = new Image(getClass().getResource("/images/user-icon.jpeg").toExternalForm());
//		Image image = null;
		ObservableList<User> users = FXCollections.observableArrayList();
		User user1 = new User(new ImageView(image), 1L, "mamlas@email.cz", "mamlas", "Jan", "Novak",
				Year.of(1985).atMonth(Month.MARCH).atDay(5), 100, 1);
		User user2 = new User(new ImageView(image), 2L, "karel@email.cz", "Karlos", "Jan", "Novak", LocalDate.now(), 55,
				2);
		User user3 = new User(new ImageView(image), 3L, "pepa@email.cz", "Pepos", "Jan", "Novak", LocalDate.now(), 33,
				3);
		User user4 = new User(new ImageView(image), 4L, "tomas@email.cz", "Thomas", "Jan", "Novak", LocalDate.now(), 66,
				4);
		User user5 = new User(new ImageView(image), 5L, "fiala@email.cz", "Fialos", "Jan", "Novak", LocalDate.now(), 77,
				5);
		User user6 = new User(new ImageView(image), 6L, "mamlas@email.cz", "mamlas", "Jan", "Novak", LocalDate.now(),
				100, 1);
		User user7 = new User(new ImageView(image), 7L, "karel@email.cz", "Karlos", "Jan", "Novak", LocalDate.now(), 55,
				2);
		User user8 = new User(new ImageView(image), 8L, "pepa@email.cz", "Pepos", "Jan", "Novak", LocalDate.now(), 33,
				3);
		User user9 = new User(new ImageView(image), 9L, "tomas@email.cz", "Thomas", "Jan", "Novak", LocalDate.now(), 66,
				4);
		User user10 = new User(new ImageView(image), 10L, "fiala@email.cz", "Fialos", "Jan", "Novak", LocalDate.now(),
				77, 5);
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		users.add(user5);
		users.add(user6);
		users.add(user7);
		users.add(user8);
		users.add(user9);
		users.add(user10);
		return users;
	}

}
