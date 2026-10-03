package com.course.patterns.observer;

public interface Observer {
    // Pull architecture: notifies observer to pull required state from subject
    void update();
}
