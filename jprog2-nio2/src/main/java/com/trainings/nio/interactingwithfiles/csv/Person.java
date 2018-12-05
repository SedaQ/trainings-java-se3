package com.trainings.nio.interactingwithfiles.csv;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Pavel Seda
 */
public class Person {

    private String id;
    private String name;
    private String email;
    private String description;
    private String age;

    public Person(String[] personCsvArray) {
        Objects.requireNonNull(personCsvArray);
        if (personCsvArray.length == 5) {
            this.id = personCsvArray[0];
            this.name = personCsvArray[1];
            this.email = personCsvArray[2];
            this.description = personCsvArray[3];
            this.age = personCsvArray[4];
        } else {
            System.out.println(personCsvArray.length);
            throw new IllegalArgumentException("Provided csv file have different number of columns");
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return Arrays.asList(id, name, email, description, age).stream().collect(Collectors.joining(","));
    }
}
