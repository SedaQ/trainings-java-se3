package com.trainings.springjdbctemplate.mapper;

import com.trainings.springjdbctemplate.dto.PersonDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Pavel Seda
 */
public class PersonMapper implements RowMapper<PersonDto> {

    private DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

    @Override
    public PersonDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        PersonDto personDto = new PersonDto();
        personDto.setId(rs.getLong("id_person"));
        personDto.setAge(rs.getInt("age"));
        personDto.setBirthday(LocalDate.parse(rs.getString("birthday"), formatter));
        personDto.setEmail(rs.getString("email"));
        personDto.setFirstName(rs.getString("first_name"));
        personDto.setNickName(rs.getString("nickname"));
        personDto.setSurname(rs.getString("surname"));
        personDto.setPwd(rs.getString("pwd").toCharArray());
        return personDto;
    }
}
