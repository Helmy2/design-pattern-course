package com.course.patterns.command;

/**
 * CLEAN CODE SOLUTION:
 * Command Interface in GoF Command Pattern:
 * Encapsulates an action and receiver into a first-class object.
 */
public interface Command {
    void execute();
    void undo();
}
