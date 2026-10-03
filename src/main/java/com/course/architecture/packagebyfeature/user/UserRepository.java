package com.course.architecture.packagebyfeature.user;

import java.util.Optional;

public interface UserRepository {
    Optional<String> findUser(String userId);
}
