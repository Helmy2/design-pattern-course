package com.course.patterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * STARTER CODE:
 * TODO: Implement Owner Invocation Handler:
 * - Allow get* methods.
 * - Disallow setGeekRating (throw IllegalAccessException).
 * - Allow set* methods (personal profile updates).
 */
public class OwnerInvocationHandler implements InvocationHandler {

    private final Person person;

    public OwnerInvocationHandler(Person person) {
        this.person = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // TODO: Step 1 - Intercept calls and block setGeekRating
        return method.invoke(person, args);
    }
}
