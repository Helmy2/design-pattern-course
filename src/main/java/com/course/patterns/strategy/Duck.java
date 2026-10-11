package com.course.patterns.strategy;

/**
 * CLEAN CODE SOLUTION:
 * The Strategy Pattern:
 * Duck delegates flying and quacking to composed behavior strategies.
 * Behaviors can be dynamically swapped at runtime via setter injection.
 */
public abstract class Duck {

    protected FlyBehavior flyBehavior;
    protected QuackBehavior quackBehavior;

    public String performFly() {
        return flyBehavior.fly();
    }

    public String performQuack() {
        return quackBehavior.quack();
    }

    public String swim() {
        return "All ducks float, even decoys!";
    }

    public abstract String display();

    public void setFlyBehavior(FlyBehavior fb) {
        this.flyBehavior = fb;
    }

    public void setQuackBehavior(QuackBehavior qb) {
        this.quackBehavior = qb;
    }
}
