package com.course.architecture.packagebyfeature.user;

import java.util.function.Consumer;

/**
 * CLEAN CODE SOLUTION:
 * Encapsulated within the 'user' feature package.
 * Communicates cross-domain events via consumer callbacks or domain events, eliminating cyclic compile dependencies.
 */
public class UserService {
    private final UserRepository userRepository;
    private final Consumer<String> onUserDeletedListener;

    public UserService(UserRepository userRepository, Consumer<String> onUserDeletedListener) {
        this.userRepository = userRepository;
        this.onUserDeletedListener = onUserDeletedListener;
    }

    public boolean isUserActive(String userId) {
        return userRepository.findUser(userId).isPresent();
    }

    public void deleteUser(String userId) {
        if (onUserDeletedListener != null) {
            onUserDeletedListener.accept(userId);
        }
    }
}
