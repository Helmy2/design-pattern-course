package com.course.patterns.strategy;

/**
 * CLEAN CODE SOLUTION:
 * Encapsulated Strategy: Flying Behavior family
 */
public interface FlyBehavior {
    String fly();

    class FlyWithWings implements FlyBehavior {
        @Override
        public String fly() {
            return "I'm flying with wings!";
        }
    }

    class FlyNoWay implements FlyBehavior {
        @Override
        public String fly() {
            return "I can't fly.";
        }
    }

    class FlyRocketPowered implements FlyBehavior {
        @Override
        public String fly() {
            return "I'm flying with a rocket propulsion boost!";
        }
    }
}
