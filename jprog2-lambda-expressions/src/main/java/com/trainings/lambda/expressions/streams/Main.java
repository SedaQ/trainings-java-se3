package com.trainings.lambda.expressions.streams;

import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;

import com.trainings.lambda.expressions.functionalinterfaces.MyAnotherFunctIface;
import com.trainings.lambda.expressions.functionalinterfaces.MyFunctionalIface;

/**
 *
 * @author Student
 */
public class Main {
    
    public static void main(String[] args){
//        Stream.generate(() -> "Pavel")
//                .filter(n -> n.length() == 5)
//                .limit(2)
//                .sorted()
//                .forEach(System.out::println);
//        
//        Stream.generate(() -> "Pavel")
//                .filter(n -> n.length() == 5)
//                .sorted()
//                .limit(2)
//                .forEach(System.out::println);
//
//        Stream.generate(() -> "Olaf Lazisson")
//            .filter(n -> n.length() == 4)
//            .limit(2)
//            .sorted()
//            .forEach(System.out::println);
//
//
//        Stream<Integer> infinite = Stream.iterate(1, x -> x + 1);
//        infinite.limit(5)
//                .peek(System.out::print)
//                .filter(x -> x % 2 == 1)
//                .forEach(System.out::print);
        
//        System.out.println(test("ahoj", "pepa", (s1,s2) -> s1.equals(s2)));
//        System.out.println(sum(1L, 2L, (n1,n2) -> ((Long) n1) + ((Long) n2)));

        IntStream is = IntStream.range(0, 6);
        System.out.println(average(is));
    }
    
    private static double average(IntStream ints) {
        IntSummaryStatistics stats = ints.summaryStatistics();
        if (stats.getCount() == 0) 
            throw new RuntimeException();
        return stats.getAverage();
    }
    
    public static boolean test(String s1, String s2, MyFunctionalIface mfi){
        return mfi.testEquality(s1, s2);
    }
    
    public static long sum(Long l1, Long l2, MyAnotherFunctIface mafi){
        return mafi.sum(l1, l2);
    }
    
}
