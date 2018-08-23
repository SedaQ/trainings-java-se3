package com.trainings.lambda.expressions.functionalinterfaces;

/**
 *
 * @author Pavel Šeda
 */
@FunctionalInterface
public interface MyAnotherFunctIface<T> {
    
    long sum(T t1, T t2);
}
