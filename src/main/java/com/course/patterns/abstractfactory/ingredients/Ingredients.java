package com.course.patterns.abstractfactory.ingredients;

public class Ingredients {

    // Interfaces for Product Family
    public interface Dough { String getName(); }
    public interface Sauce { String getName(); }
    public interface Cheese { String getName(); }
    public interface Clams { String getName(); }

    // New York Family
    public static class ThinCrustDough implements Dough {
        @Override public String getName() { return "Thin Crust Dough"; }
    }
    public static class MarinaraSauce implements Sauce {
        @Override public String getName() { return "Marinara Sauce"; }
    }
    public static class ReggianoCheese implements Cheese {
        @Override public String getName() { return "Reggiano Cheese"; }
    }
    public static class FreshClams implements Clams {
        @Override public String getName() { return "Fresh Long Island Clams"; }
    }

    // Chicago Family
    public static class ThickCrustDough implements Dough {
        @Override public String getName() { return "Thick Deep Dish Crust Dough"; }
    }
    public static class PlumTomatoSauce implements Sauce {
        @Override public String getName() { return "Plum Tomato Sauce"; }
    }
    public static class MozzarellaCheese implements Cheese {
        @Override public String getName() { return "Shredded Mozzarella Cheese"; }
    }
    public static class FrozenClams implements Clams {
        @Override public String getName() { return "Frozen Lake Michigan Clams"; }
    }
}
