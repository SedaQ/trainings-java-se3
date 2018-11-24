package com.trainings.springjdbctemplate;

import com.trainings.springjdbctemplate.dbconnection.HikariDataSourceJdbcTemplate;
import com.trainings.springjdbctemplate.dto.PersonDto;
import com.trainings.springjdbctemplate.repository.PersonRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;


/**
 * @author Pavel Seda
 */
public class App {


    public static void main(String[] args) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(HikariDataSourceJdbcTemplate.getDataSource());

        PersonRepository personRepository = new PersonRepository(jdbcTemplate);

        PersonDto personDto = personRepository.getPersonById(1L);
        System.out.println("Person with id 1: " + personDto);

        System.out.println(System.lineSeparator() + "All persons in database..");

        List<PersonDto> persons = personRepository.getAllPersons();
        persons.forEach(System.out::println);
    }
}
