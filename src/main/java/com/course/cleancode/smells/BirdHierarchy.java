package com.course.cleancode.smells;

/**
 * STARTER CODE:
 * OO Abuser Smell: Refused Bequest (LSP Violation).
 * Subclasses forced to inherit and override methods with UnsupportedOperationException.
 */
public class BirdHierarchy {

    public static abstract class Bird {
        public abstract String eat();
        public abstract String fly();
    }

    public static class Eagle extends Bird {
        @Override
        public String eat() {
            return "Eagle eating";
        }

        @Override
        public String fly() {
            return "Eagle soaring high";
        }
    }

    public static class Penguin extends Bird {
        @Override
        public String eat() {
            return "Penguin eating fish";
        }

        @Override
        public String fly() {
            // Refused bequest: breaks Liskov Substitution Principle!
            throw new UnsupportedOperationException("Penguins cannot fly!");
        }
    }
}
