package com.course.patterns.abstractfactory;

import com.course.patterns.abstractfactory.ingredients.Ingredients.Cheese;
import com.course.patterns.abstractfactory.ingredients.Ingredients.Clams;
import com.course.patterns.abstractfactory.ingredients.Ingredients.Dough;
import com.course.patterns.abstractfactory.ingredients.Ingredients.Sauce;

import java.util.ArrayList;
import java.util.List;

/**
 * Base Pizza product.
 * In Factory Method (07), ingredients were represented by raw strings or ad-hoc fields.
 * Here in 08, ingredients belong to formal component interfaces (Dough, Sauce, Cheese, Clams).
 */
public abstract class Pizza {
    protected String name;
    protected Dough dough;
    protected Sauce sauce;
    protected Cheese cheese;
    protected Clams clams;
    protected List<String> toppings = new ArrayList<>();
    protected String cutStyle = "diagonal";

    public abstract void prepare();

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
    public void setName(String name) { this.name = name; }
    public Dough getDough() { return dough; }
    public Sauce getSauce() { return sauce; }
    public Cheese getCheese() { return cheese; }
    public Clams getClams() { return clams; }
    public List<String> getToppings() { return List.copyOf(toppings); }
    public String getCutStyle() { return cutStyle; }
}
