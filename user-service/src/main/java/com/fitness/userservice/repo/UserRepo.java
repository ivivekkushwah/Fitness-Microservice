package com.fitness.userservice.repo;

import com.fitness.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);

    User getByEmail(String email);
}
