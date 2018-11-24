package com.trainings.jdbc;

import com.trainings.jdbc.dto.PersonDTO;
import com.trainings.jdbc.repository.PersonRepository;

import java.util.List;

/**
 * @author Pavel Seda
 */
public class App {

    public static void main(String[] args) {
        PersonRepository pm = new PersonRepository();

        PersonDTO personById = pm.getPersonById(4);
        System.out.println(personById);

        PersonDTO personByIdPrepStatement = pm.getPersonByIdUsingPreparedStatement(4);
        System.out.println(personByIdPrepStatement);

        int affectedPersons = pm.updatePersonAge(1, 18);
        System.out.println(affectedPersons);

        List<PersonDTO> person = pm.getAllPersons();
        person.forEach(System.out::println);

    }
}
