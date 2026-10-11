package com.course.patterns.strategy;

public class ModelDuck extends Duck {

    public ModelDuck() {
        this.flyBehavior = new FlyBehavior.FlyNoWay();
        this.quackBehavior = new QuackBehavior.MuteQuack();
    }

    @Override
    public String display() {
        return "I'm a model duck";
    }
}
