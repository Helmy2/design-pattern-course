package com.course.patterns.decorator.problem;

/**
 * STARTER PROBLEM CODE:
 *
 * Demonstrates the flaws BEFORE introducing the Decorator pattern:
 *
 * 1. Subclass Explosion Anti-Pattern:
 *    Attempting to create concrete subclasses for every possible combination:
 *    DarkRoastWithMocha, DarkRoastWithMochaAndWhip, EspressoWithSoy, etc.
 *
 * 2. God Superclass with Boolean Flags Anti-Pattern:
 *    Beverage maintains flags (hasMocha, hasWhip, hasSoy).
 *    Flaws:
 *    - Price changes in condiments force modifications to the base class (violates OCP).
 *    - New condiments require adding methods to the superclass.
 *    - Customers cannot order a "Double Mocha" (a boolean cannot track quantity).
 *    - Beverages like IcedTea inherit irrelevant condiments like steamed milk or whip.
 */
public class BeverageInheritanceProblem {

    public static class RigidBeverageWithFlags {
        private String description = "Unknown Beverage";
        private boolean mocha;
        private boolean whip;
        private boolean soy;

        public double cost() {
            double total = 1.99; // Base coffee price
            if (hasMocha()) total += 0.20;
            if (hasWhip()) total += 0.10;
            if (hasSoy()) total += 0.15;
            return total;
        }

        public boolean hasMocha() { return mocha; }
        public void setMocha(boolean mocha) { this.mocha = mocha; }

        public boolean hasWhip() { return whip; }
        public void setWhip(boolean whip) { this.whip = whip; }

        public boolean hasSoy() { return soy; }
        public void setSoy(boolean soy) { this.soy = soy; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }

    // Subclass explosion attempt:
    public static class DarkRoastWithMochaAndWhip {
        public double cost() {
            return 0.99 + 0.20 + 0.10; // Hardcoded rigid pricing
        }
        public String getDescription() {
            return "Dark Roast with Mocha and Whip";
        }
    }
}
