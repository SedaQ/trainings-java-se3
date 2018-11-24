package com.trainings.jdbc.repository;


import com.trainings.jdbc.dbconnection.DBConnectionDriverManager;
import com.trainings.jdbc.dbconnection.DBConnectionHikariDataSource;
import com.trainings.jdbc.dto.PersonDTO;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Pavel Seda
 */
public class PersonRepository {

    private DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

    public List<PersonDTO> getAllPersons() {
        try (Connection conn = DBConnectionHikariDataSource.getConnection(); Statement statement = conn.createStatement(); ResultSet rs = statement.executeQuery("SELECT * FROM person");) {
            return mapPersonTableToDTO(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public PersonDTO getPersonById(long id) {
        try (Connection conn = DBConnectionHikariDataSource.getConnection(); Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM person WHERE id_person=" + id);) {
            PersonDTO person = null;
            if (rs.next()) {
                person = mapPersonResultSetToDTO(rs);
            }
            return person;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("Not person is present");
    }

    public PersonDTO getPersonByIdUsingPreparedStatement(long id) {
        PreparedStatement prepStatement = null;
        String findByIdString = "SELECT * FROM person WHERE id_person = ?";
        ResultSet rs = null;
        try (Connection conn = DBConnectionHikariDataSource.getConnection();) {
            prepStatement = conn.prepareStatement(findByIdString);
            prepStatement.setLong(1, id);
            rs = prepStatement.executeQuery();
            PersonDTO person = null;
            if (rs.next()) {
                person = mapPersonResultSetToDTO(rs);
            }
            return person;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (prepStatement != null) {
                try {
                    prepStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        throw new NullPointerException("Not person is present");
    }

    public int createPerson() {
        try (Connection conn = DBConnectionHikariDataSource.getConnection(); Statement statement = conn.createStatement();) {
            int affectedRows =
                    statement.executeUpdate("INSERT INTO person VALUES (50, 50, 1983-05-25, qweseznam.cz, David, Smeták, myPwd, Kunz)");
            return affectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updatePersonAge(int personID, int age) {
        try (Connection conn = DBConnectionHikariDataSource.getConnection(); Statement statement = conn.createStatement();) {
            int affectedRows = statement.executeUpdate("UPDATE person SET age = " + age + " WHERE id_person = " + personID);
            return affectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private List<PersonDTO> mapPersonTableToDTO(ResultSet rs) {
        List<PersonDTO> persons = new ArrayList<>();
        try {
            while (rs.next()) {
                PersonDTO personDTO = mapPersonResultSetToDTO(rs);
                persons.add(personDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }

    private PersonDTO mapPersonResultSetToDTO(ResultSet rs) throws SQLException {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(rs.getLong("id_person"));
        personDTO.setAge(rs.getInt("age"));
        personDTO.setBirthday(LocalDate.parse(rs.getString("birthday"), formatter));
        personDTO.setEmail(rs.getString("email"));
        personDTO.setFirstName(rs.getString("first_name"));
        personDTO.setNickName(rs.getString("nickname"));
        personDTO.setSurname(rs.getString("surname"));
        personDTO.setPwd(rs.getString("pwd").toCharArray());
        return personDTO;
    }
}
