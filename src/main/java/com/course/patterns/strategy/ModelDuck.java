package com.course.patterns.strategy;

/**
 * STARTER CODE:
 * Model duck starts out unable to fly, but allows dynamic behavior swapping at runtime.
 */
public class ModelDuck extends Duck {

    public ModelDuck() {
        this.flyBehavior = new FlyBehavior.FlyNoWay();
        this.quackBehavior = new QuackBehavior.Quack();
    }

    @Override
    public String display() {
        return "I'm a model duck";
    }
}
