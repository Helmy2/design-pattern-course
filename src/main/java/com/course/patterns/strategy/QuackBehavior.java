package com.course.patterns.strategy;

/**
 * CLEAN CODE SOLUTION:
 * Encapsulated Strategy: Quacking Behavior family
 */
public interface QuackBehavior {
    String quack();

    class Quack implements QuackBehavior {
        @Override
        public String quack() {
            return "Quack";
        }
    }

    class Squeak implements QuackBehavior {
        @Override
        public String quack() {
            return "Squeak";
        }
    }

    class MuteQuack implements QuackBehavior {
        @Override
        public String quack() {
            return "<< Silence >>";
        }
    }
}
