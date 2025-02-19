package com.dharmaraj.splitwise.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dharmaraj.splitwise.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 
    public Optional<User> findUserById(Long userId);
}
