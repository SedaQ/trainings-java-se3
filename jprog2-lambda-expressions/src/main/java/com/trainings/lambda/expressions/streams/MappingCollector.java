package com.trainings.lambda.expressions.streams;

import java.util.*;
import java.util.stream.*;

import com.trainings.lambda.expressions.Employee;

/**
 *
 * @author Student
 */
public class MappingCollector {
	
    static List<Employee> employeeList
      = Arrays.asList(new Employee("Tom Jones", 45, 15000.00),
      new Employee("Harry Andrews", 45, 7000.00),
      new Employee("Ethan Hardy", 65, 8000.00),
      new Employee("Nancy Smith", 22, 10000.00),
      new Employee("Deborah Sprightly", 29, 9000.00));
 
  public static void main(String[] args) {
    List<String> employeeNames = employeeList.stream()
        .collect(Collectors.mapping(Employee::getName, Collectors.toList()));
    System.out.println("List of employee names:" + employeeNames);
    
    Optional<Integer> maxAge = employeeList.stream()
      .peek(System.out::println)
      .collect(Collectors.mapping((Employee emp) -> emp.getAge(), Collectors.maxBy(Integer::compareTo)));
    System.out.println("Max Age: " + maxAge.get());
  }
}
