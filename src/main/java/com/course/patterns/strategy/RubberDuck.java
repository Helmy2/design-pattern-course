package com.course.patterns.strategy;

public class RubberDuck extends Duck {

    public RubberDuck() {
        this.flyBehavior = new FlyBehavior.FlyNoWay();
        this.quackBehavior = new QuackBehavior.Squeak();
    }

    @Override
    public String display() {
        return "I'm a yellow rubber duckie";
    }
}
