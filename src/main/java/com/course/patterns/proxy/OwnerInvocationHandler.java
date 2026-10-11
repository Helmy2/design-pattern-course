package com.course.patterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * CLEAN CODE SOLUTION:
 * Owner Protection Proxy Invocation Handler:
 * - Allows inspection via getters.
 * - Allows owner to update name and interests.
 * - Disallows owner from modifying their own rating score!
 */
public class OwnerInvocationHandler implements InvocationHandler {

    private final Person person;

    public OwnerInvocationHandler(Person person) {
        this.person = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            if (method.getName().startsWith("get")) {
                return method.invoke(person, args);
            } else if (method.getName().equals("setGeekRating")) {
                throw new IllegalAccessException("Owners cannot modify their own rating score!");
            } else if (method.getName().startsWith("set")) {
                return method.invoke(person, args);
            }
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
        return null;
    }
}
