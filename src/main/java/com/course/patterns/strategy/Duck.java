package com.course.patterns.strategy;

/**
 * Strategy Client Context:
 * Holds strategy references via composition and delegates execution.
 */
public abstract class Duck {

    protected FlyBehavior flyBehavior;
    protected QuackBehavior quackBehavior;

    public String performFly() {
        return flyBehavior != null ? flyBehavior.fly() : "No fly behavior";
    }

    public String performQuack() {
        return quackBehavior != null ? quackBehavior.quack() : "No quack behavior";
    }

    public String swim() {
        return "All ducks float, even decoys!";
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public abstract String display();
}
