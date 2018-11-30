/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trainings.swing.controller;

import com.trainings.swing.dto.PersonDto;
import com.trainings.swing.model.PersonModel;
import java.util.List;

/**
 *
 * @author Pavel Seda
 */
public class PersonsController {

    private PersonModel personModel;

    public PersonsController(PersonModel personModel) {
        this.personModel = personModel;
    }

    public List<PersonDto> findAllPersons() {
        return personModel.findAllPersons();
    }
}
