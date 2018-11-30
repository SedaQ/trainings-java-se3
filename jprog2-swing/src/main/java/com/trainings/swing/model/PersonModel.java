package com.trainings.swing.model;

import com.trainings.swing.dto.PersonDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Pavel Seda
 */
public class PersonModel {

    public PersonModel() {
    }

    public List<PersonDto> findAllPersons() {
        //mocked data, in next iteration it will be retrieved from DB
        return new ArrayList<>(Arrays.asList(new PersonDto(1, "pavel", "seda", "SedaQ"), new PersonDto(2, "Marcela", "Novakova", "Marci"),
                new PersonDto(3, "Karla", "Novakova", "Mozek"),
                new PersonDto(4, "Jonas", "Novakova", "Jony"),
                new PersonDto(5, "Monika", "Novakova", "Monca"),
                new PersonDto(6, "Mikulas", "Novakova", "Miki"),
                new PersonDto(7, "Mecias", "Novakova", "Meci"),
                new PersonDto(8, "Satan", "Novakova", "Saty")));
    }

}
