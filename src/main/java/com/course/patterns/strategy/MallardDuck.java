package com.course.patterns.strategy;

public class MallardDuck extends Duck {

    public MallardDuck() {
        this.flyBehavior = new FlyBehavior.FlyWithWings();
        this.quackBehavior = new QuackBehavior.Quack();
    }

    @Override
    public String display() {
        return "I'm a real Mallard duck";
    }
}
