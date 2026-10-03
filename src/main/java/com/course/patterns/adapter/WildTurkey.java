package com.course.patterns.adapter;

public class WildTurkey implements Turkey {
    @Override
    public String gobble() {
        return "Gobble gobble";
    }

    @Override
    public String fly() {
        return "I'm flying a short distance";
    }
}
