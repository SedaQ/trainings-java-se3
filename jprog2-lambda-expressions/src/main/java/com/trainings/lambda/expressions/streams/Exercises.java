package com.trainings.lambda.expressions.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author Pavel Šeda
 *
 */
public class Exercises {

    /**
     * Collect to Map<Boolean, Set<String>> listOfStrings dependening if the string is shorter or same length as 7
     */
    private static void partitioningBy(){
        List<String> listOfStrings = Arrays.asList("pavel", "tigers", "bears", "po23op4234", "kokomotoko");
        // code here
        //System.out.println(map);//
    }
    
    private static void something(){
        Stream<String> listOfStrings = Stream.of("lions", "tigers", "bears");
        Map<Integer, Set<String>> map = listOfStrings
                .collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toSet()));
    }
    
    
    
    
    private static void whatDoesItDo(){
//        Stream<String> listOfStrings = Stream.of("lions", "tigers", "bears");
//        Map<Integer, Optional<Character>>  map = listOfStrings.collect(
//                Collectors.groupingBy(
//                    String::length,
//                    Collectors.mapping(s -> s.charAt(0),
//                    Collectors.minBy(Comparator.naturalOrder()))));
//        System.out.println(map); // {5=Optional[b], 6=Optional[t]}
    }
    
    
    private static void partitioningBySolution(){
        List<String> listOfStrings = Arrays.asList("pavel", "tigers", "bears", "po23op4234", "kokomotoko");
        Map<Boolean, Set<String>> map = listOfStrings.stream()
                                                .collect(
                                                        Collectors.partitioningBy(s -> s.length() <= 7, Collectors.toSet()));
        System.out.println(map);
    }

}
