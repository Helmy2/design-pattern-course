package com.course.patterns.factorymethod;

import java.util.ArrayList;
import java.util.List;

public abstract class Pizza {
    protected String name;
    protected String dough;
    protected String sauce;
    protected List<String> toppings = new ArrayList<>();
    protected String cutStyle = "diagonal";

    public void prepare() {
        // Standard preparation lifecycle
    }

    public void bake() {
        // Standard bake 25 min at 350
    }

    public void cut() {
        cutStyle = "diagonal";
    }

    public void box() {
        // Official PizzaStore box
    }

    public String getName() { return name; }
    public String getDough() { return dough; }
    public String getSauce() { return sauce; }
    public List<String> getToppings() { return List.copyOf(toppings); }
    public String getCutStyle() { return cutStyle; }
}
