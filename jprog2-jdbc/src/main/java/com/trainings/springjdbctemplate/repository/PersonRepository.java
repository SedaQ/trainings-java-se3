package com.trainings.springjdbctemplate.repository;

import com.trainings.springjdbctemplate.dto.PersonDto;
import com.trainings.springjdbctemplate.mapper.PersonMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author Pavel Seda
 */
public class PersonRepository {

    private JdbcTemplate jdbcTemplate;

    public PersonRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<PersonDto> getAllPersons() {
        return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
    }

    public PersonDto getPersonById(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM person WHERE id_person = ?", new Object[]{id}, new PersonMapper());
    }

}
