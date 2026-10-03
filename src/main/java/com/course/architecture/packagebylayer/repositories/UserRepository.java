package com.course.architecture.packagebylayer.repositories;

import java.util.Optional;

public interface UserRepository {
    Optional<String> findUser(String userId);
}
