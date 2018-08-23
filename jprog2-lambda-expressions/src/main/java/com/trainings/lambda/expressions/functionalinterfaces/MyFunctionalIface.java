package com.trainings.lambda.expressions.functionalinterfaces;

/**
 *
 * @author Pavel Šeda
 */
@FunctionalInterface
public interface MyFunctionalIface<T> {
    
    boolean testEquality(T t1, T t2);
    
    default void print(){
//        System.out.println();
    }
    
}
