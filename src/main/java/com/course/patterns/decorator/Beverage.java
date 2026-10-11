package com.course.patterns.decorator;

/**
 * STARTER ANTI-PATTERN: God Superclass with Boolean Flags
 *
 * Flaws:
 * 1. Price changes in condiments force modifications to this base class (violates OCP).
 * 2. New condiments require adding new methods and modifying cost() here.
 * 3. Customers cannot order a "Double Mocha" (a boolean flag cannot track quantity).
 * 4. Beverages like IcedTea or HerbalTea inherit irrelevant condiments like steamed milk or whip.
 */
public abstract class Beverage {
    protected String description = "Unknown Beverage";
    private boolean milk;
    private boolean soy;
    private boolean mocha;
    private boolean whip;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double cost() {
        double condimentCost = 0.0;
        if (hasMilk()) condimentCost += 0.10;
        if (hasSoy()) condimentCost += 0.15;
        if (hasMocha()) condimentCost += 0.20;
        if (hasWhip()) condimentCost += 0.10;
        return condimentCost;
    }

    public boolean hasMilk() { return milk; }
    public void setMilk(boolean milk) { this.milk = milk; }

    public boolean hasSoy() { return soy; }
    public void setSoy(boolean soy) { this.soy = soy; }

    public boolean hasMocha() { return mocha; }
    public void setMocha(boolean mocha) { this.mocha = mocha; }

    public boolean hasWhip() { return whip; }
    public void setWhip(boolean whip) { this.whip = whip; }
}
