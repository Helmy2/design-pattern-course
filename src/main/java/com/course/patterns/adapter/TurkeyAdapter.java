package com.course.patterns.adapter;

/**
 * CLEAN CODE SOLUTION:
 * Object Adapter:
 * - Implements target interface (Duck)
 * - Composes adaptee (Turkey)
 * - Translates quack() into turkey.gobble()
 * - Translates fly() by repeating turkey short flight bursts 5 times
 */
public class TurkeyAdapter implements Duck {

    private final Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public String quack() {
        return turkey.gobble();
    }

    @Override
    public String fly() {
        StringBuilder flights = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            if (i > 0) {
                flights.append("\n");
            }
            flights.append(turkey.fly());
        }
        return flights.toString();
    }
}
