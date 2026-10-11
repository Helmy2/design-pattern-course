package com.course.patterns.strategy;

public class RubberDuck extends Duck {

    @Override
    public String display() {
        return "I'm a yellow rubber duckie";
    }

    @Override
    public String quack() {
        return "Squeak";
    }

    // RubberDuck inherits fly() from Duck superclass ("I'm flying!!") - the inheritance smell!
}
