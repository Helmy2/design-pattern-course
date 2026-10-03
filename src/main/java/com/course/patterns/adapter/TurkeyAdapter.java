package com.course.patterns.adapter;

/**
 * STARTER CODE:
 * TODO: Implement Object Adapter:
 * - Implements target Duck interface
 * - Holds Turkey reference via composition
 * - Translates quack() to gobble()
 * - Translates fly() by flying 5 times in short bursts to mimic a full flight
 */
public class TurkeyAdapter implements Duck {

    private final Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public String quack() {
        // TODO: Step 1 - Translate to turkey.gobble()
        return null;
    }

    @Override
    public String fly() {
        // TODO: Step 2 - Repeat turkey.fly() 5 times to mimic duck flight
        return null;
    }
}
