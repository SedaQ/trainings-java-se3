/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trainings.swing.dto;

/**
 *
 * @author Pavel Seda
 */
public class PersonDto {

    private long id;
    private String firstName;
    private String lastName;
    private String nickname;

    public PersonDto() {
    }

    public PersonDto(long id, String firstName, String lastName, String nickname) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "PersonDto{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", nickname=" + nickname + '}';
    }

}
