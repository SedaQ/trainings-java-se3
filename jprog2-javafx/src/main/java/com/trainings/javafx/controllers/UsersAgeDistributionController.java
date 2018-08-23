package com.trainings.javafx.controllers;

import com.trainings.javafx.model.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class UsersAgeDistributionController {

	@FXML
	private PieChart ageDistributionPieChart;

	public UsersAgeDistributionController() {
	}

	@FXML
	private void initialize() {

	}

	public void initPieChartData(ObservableList<User> users) {
		int ageUnder25 = 0;
		int ageBetween25And35 = 0;
		int ageBetween35And55 = 0;
		int ageAbove55 = 0;
		for (User user : users) {
			int age = user.getAge();
			if (age < 25) {
				ageUnder25++;
			} else if (age >= 25 && age < 35) {
				ageBetween25And35++;
			} else if (age >= 35 && age < 55) {
				ageBetween35And55++;
			} else if (age >= 55) {
				ageAbove55++;
			}
		}
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("0-24", ageUnder25), new PieChart.Data("25-34", ageBetween25And35),
				new PieChart.Data("35-54", ageBetween35And55), new PieChart.Data("55+", ageAbove55));
		ageDistributionPieChart.setData(pieChartData);
		ageDistributionPieChart.setTitle("Age Distribution in our Company");
	}
}
