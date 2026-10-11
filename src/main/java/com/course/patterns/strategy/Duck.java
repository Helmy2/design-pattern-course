package com.course.patterns.strategy;

/**
 * STARTER ANTI-PATTERN: Rigid Superclass Inheritance
 *
 * Flaws:
 * 1. Forcing all subclasses to inherit fly() and quack() causes non-flying ducks (e.g. RubberDuck)
 *    to fly across the screen!
 * 2. Overriding methods in subclasses to do nothing creates duplication and prevents dynamic runtime behavior swapping.
 */
public abstract class Duck {

    public String quack() {
        return "Quack";
    }

    public String swim() {
        return "All ducks float, even decoys!";
    }

    public String fly() {
        return "I'm flying!!";
    }

    public abstract String display();
}
