package com.trainings.lambda.expressions;

import java.util.Optional;

/**
 *
 * @author Student
 */
public class Student {
    
    private Long id;
    private String nationality;
    
    public Student(){}

    public Student(Long id, String nationality) {
        this.id = id;
        this.nationality = nationality;
    }

    public Optional<Long> getId() {
        return Optional.ofNullable(id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    
}
