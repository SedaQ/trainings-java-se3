package com.trainings.nio.interactingwithfiles.csv;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The example of reading CSV file to the Person object (including the case of ignoring quotes in particular columns in csv.
 *
 * @author Pavel Seda
 */
public class ReadCsvFile {

    public static void main(String[] args) {
        try {
            List<Person> fileLines = Files
                    .lines(Paths.get("./jprog2-nio2/src/main/resources/test-files/persons.csv"), Charset.forName("CP1252"))
                    .filter(str -> !str.startsWith("#"))
                    .map(line -> new Person(line.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)")))
                    .filter(person -> !person.getName().equals("Nela"))
                    .collect(Collectors.toList());

            fileLines.forEach(System.out::println);

            // the second parameter of write method must contain iterable of Strings: (Iterable<T extends Charsequence>)
            List<String> persons = fileLines.stream()
                    .map(Person::toString)
                    .collect(Collectors.toList());
            Files.write(Paths.get("./jprog2-nio2/src/main/resources/test-files/persons-modified-write.csv"), persons, Charset.forName("CP1252"));
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
