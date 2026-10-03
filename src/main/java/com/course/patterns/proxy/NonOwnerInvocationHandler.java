package com.course.patterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * STARTER CODE:
 * TODO: Implement Non-Owner Invocation Handler:
 * - Allow get* methods.
 * - Allow setGeekRating (rating other profiles).
 * - Disallow any other set* methods (modifying someone else's personal info).
 */
public class NonOwnerInvocationHandler implements InvocationHandler {

    private final Person person;

    public NonOwnerInvocationHandler(Person person) {
        this.person = person;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // TODO: Step 2 - Intercept calls and block set* (except setGeekRating)
        return method.invoke(person, args);
    }
}
