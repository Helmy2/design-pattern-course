package com.course.architecture.overengineering;

import java.util.Optional;

/**
 * STARTER CODE:
 * The Cost of Over-Engineering (Accidental Complexity):
 * 5 classes and interfaces just to fetch a user and format their name!
 */
public class OverEngineeredUserService {

    public record User(Long id, String firstName, String lastName) {}
    public record UserDisplayNameDto(Long id, String displayName) {}

    // Smell 1: Pointless 1:1 Interface
    public interface IUserService {
        UserDisplayNameDto getUserDisplayName(UserIdSpecification spec);
    }

    // Smell 2: Unnecessary Specification object for a simple primary key lookup
    public record UserIdSpecification(Long id) {}

    // Smell 3: Unnecessary Strategy interface for simple string concatenation
    public interface NameFormattingStrategy {
        String format(String first, String last);
    }

    public static class DefaultNameFormattingStrategy implements NameFormattingStrategy {
        @Override
        public String format(String first, String last) {
            return first + " " + last;
        }
    }

    public interface UserRepository {
        Optional<User> findById(Long id);
    }

    // Smell 4: Over-abstracted service implementation
    public static class UserServiceImpl implements IUserService {
        private final UserRepository userRepository;
        private final NameFormattingStrategy formattingStrategy;

        public UserServiceImpl(UserRepository userRepository, NameFormattingStrategy formattingStrategy) {
            this.userRepository = userRepository;
            this.formattingStrategy = formattingStrategy;
        }

        @Override
        public UserDisplayNameDto getUserDisplayName(UserIdSpecification spec) {
            User user = userRepository.findById(spec.id())
                    .orElseThrow(() -> new IllegalArgumentException("User not found: " + spec.id()));
            String formatted = formattingStrategy.format(user.firstName(), user.lastName());
            return new UserDisplayNameDto(user.id(), formatted);
        }
    }
}
