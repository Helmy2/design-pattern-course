package com.course.patterns.adapter.problem;

import com.course.patterns.adapter.Duck;
import com.course.patterns.adapter.Turkey;

/**
 * STARTER PROBLEM CODE:
 *
 * Client simulator expecting target Duck interface cannot consume
 * third-party WildTurkey directly due to mismatched method signatures
 * (gobble() vs quack(), short burst fly() vs continuous fly()).
 */
public class IncompatibleInterfaceProblem {

    public static String runDuckSimulation(Duck duck) {
        return duck.quack() + " | " + duck.fly();
    }
}
