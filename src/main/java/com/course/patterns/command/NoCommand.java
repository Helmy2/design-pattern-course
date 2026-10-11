package com.course.patterns.command;

/**
 * CLEAN CODE SOLUTION:
 * Null Object Pattern:
 * Eliminates null checks when slots are unoccupied.
 */
public class NoCommand implements Command {
    @Override public void execute() {}
    @Override public void undo() {}
}
