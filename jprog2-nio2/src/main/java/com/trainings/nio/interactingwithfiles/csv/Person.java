package com.trainings.nio.interactingwithfiles.csv;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Pavel Seda
 */
public class Person {

    private long id;
    private String name;
    private String email;
    private String description;
    private int age;

    public Person(String[] personCsvArray) {
        Objects.requireNonNull(personCsvArray);
        if (personCsvArray.length == 5) {
            this.id = Long.parseLong(personCsvArray[0]);
            this.name = personCsvArray[1];
            this.email = personCsvArray[2];
            this.description = personCsvArray[3];
            this.age = Integer.parseInt(personCsvArray[4].trim());
        } else {
            System.out.println(personCsvArray.length);
            throw new IllegalArgumentException("Provided csv file have different number of columns");
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return Arrays.asList(String.valueOf(id), name, email, description, String.valueOf(age)).stream().collect(Collectors.joining(","));
    }
}
