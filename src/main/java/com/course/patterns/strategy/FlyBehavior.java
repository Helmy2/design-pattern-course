package com.course.patterns.strategy;

public interface FlyBehavior {
    String fly();

    class FlyWithWings implements FlyBehavior {
        @Override public String fly() { return "I'm flying!!"; }
    }

    class FlyNoWay implements FlyBehavior {
        @Override public String fly() { return "I can't fly"; }
    }

    class FlyRocketPowered implements FlyBehavior {
        @Override public String fly() { return "I'm flying with a rocket!"; }
    }
}
