package com.dharmaraj.splitwise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dharmaraj.splitwise.models.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    
}
