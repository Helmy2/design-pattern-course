package com.course.patterns.strategy;

/**
 * STARTER CODE:
 * TODO: Configure MallardDuck with Quack and FlyWithWings strategies.
 */
public class MallardDuck extends Duck {

    public MallardDuck() {
        // TODO: Step 1 - Initialize quackBehavior and flyBehavior
        this.quackBehavior = new QuackBehavior.Quack();
        this.flyBehavior = new FlyBehavior.FlyWithWings();
    }

    @Override
    public String display() {
        return "I'm a real Mallard duck";
    }
}
