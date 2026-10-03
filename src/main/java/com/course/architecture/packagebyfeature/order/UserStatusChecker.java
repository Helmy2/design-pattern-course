package com.course.architecture.packagebyfeature.order;

/**
 * Domain Port: Inverted dependency preventing order feature from coupling directly to user package.
 */
@FunctionalInterface
public interface UserStatusChecker {
    boolean isUserActive(String userId);
}
