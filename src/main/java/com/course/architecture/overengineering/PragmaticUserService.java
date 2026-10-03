package com.course.architecture.overengineering;

import java.util.Optional;

/**
 * CLEAN CODE SOLUTION:
 * Pragmatic engineering adhering to KISS, YAGNI, and the Rule of Three.
 * Eliminates 1:1 interfaces, useless specifications, and premature strategy wrappers.
 */
public class PragmaticUserService {

    public record User(Long id, String firstName, String lastName) {}
    public record UserDisplayNameDto(Long id, String displayName) {}

    public interface UserRepository {
        Optional<User> findById(Long id);
    }

    private final UserRepository userRepository;

    public PragmaticUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDisplayNameDto getUserDisplayName(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

        return new UserDisplayNameDto(user.id(), user.firstName() + " " + user.lastName());
    }
}
