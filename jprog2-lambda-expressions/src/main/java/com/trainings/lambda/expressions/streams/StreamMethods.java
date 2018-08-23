/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trainings.lambda.expressions.streams;

import java.util.*;
import java.util.stream.Collectors;

import com.trainings.lambda.expressions.Student;

/**
 *
 * @author Student
 */
public class StreamMethods {
    
    public static void main(String[] args){
        
        List<Student> listOfStudents = createStudents();
        listOfStudents.stream().collect(Collectors.toCollection(ArrayList::new));
//        
        Map<String, List<Student>> groupedByNationality = listOfStudents.stream()
                .peek(s-> System.out.println(s.getId().get()))
                .limit(2)
                .collect(Collectors.groupingBy(Student::getNationality));
//        
//        List<String> list1 = Arrays.asList("1","2","3","4");
//        list1.forEach(System.out::print);
//        list1.stream().peek(System.out::println).forEach(s -> System.out.print(s + " "));
//        
//        listOfStudents.stream().max(Comparator.comparing(Student::getId));
        
        
//        String s12 = null;
//        Optional<String> opt1 = Optional.ofNullable(s12);
//        Optional<String> opt1 = Optional.of(s12);
        
        
        
//        if(opt1.isPresent()){
//            opt1.get();
//        }
//        String s1 = opt1.orElseThrow(NullPointerException::new);
        
        
    }
    
    private static List<Student> createStudents(){
        Student s1 = new Student(1L,"cze");
        Student s2 = new Student(2L,"cze");
        Student s3 = new Student(3L,"fr");
        Student s4 = new Student(4L,"spain");
       return Arrays.asList(s1,s2,s3,s4);
    }

}
