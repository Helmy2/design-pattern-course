package com.course.cleancode.smells;

/**
 * CLEAN CODE SOLUTION:
 * Fixes Refused Bequest (LSP Violation) using Interface Segregation.
 * Penguin only implements Bird, and is never forced to provide an unsupported fly() method.
 */
public class BirdHierarchy {

    public interface Bird {
        String eat();
    }

    public interface FlyingBird extends Bird {
        String fly();
    }

    public static class Eagle implements FlyingBird {
        @Override
        public String eat() {
            return "Eagle eating";
        }

        @Override
        public String fly() {
            return "Eagle soaring high";
        }
    }

    public static class Penguin implements Bird {
        @Override
        public String eat() {
            return "Penguin eating fish";
        }
        // No fly() method forced onto Penguin!
    }
}
