package com.course.patterns.adapter;

/**
 * Client simulator expecting target Duck interface.
 * Can consume any implementation of Duck (e.g. MallardDuck),
 * but cannot directly consume incompatible interfaces like Turkey.
 */
public class DuckSimulator {

    public String simulate(Duck duck) {
        return duck.quack() + " | " + duck.fly();
    }
}
