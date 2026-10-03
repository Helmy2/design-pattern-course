package com.course.patterns.command;

/**
 * Null Object Pattern:
 * Used as a default for unassigned remote slots to eliminate null checks.
 */
public class NoCommand implements Command {
    @Override public void execute() {}
    @Override public void undo() {}
}
