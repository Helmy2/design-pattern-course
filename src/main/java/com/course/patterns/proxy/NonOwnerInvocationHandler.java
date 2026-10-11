package com.course.patterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * CLEAN CODE SOLUTION:
 * Non-Owner Protection Proxy Invocation Handler:
 * - Allows inspection via getters.
 * - Allows outside users to rate the person (setGeekRating).
 * - Disallows outside users from altering personal details (setName, setInterests, setGender).
 */
public class NonOwnerInvocationHandler implements InvocationHandler {

    private final Person person;

    public NonOwnerInvocationHandler(Person person) {
        this.person = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            if (method.getName().startsWith("get")) {
                return method.invoke(person, args);
            } else if (method.getName().equals("setGeekRating")) {
                return method.invoke(person, args);
            } else if (method.getName().startsWith("set")) {
                throw new IllegalAccessException("You cannot alter another user's personal profile!");
            }
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
        return null;
    }
}
