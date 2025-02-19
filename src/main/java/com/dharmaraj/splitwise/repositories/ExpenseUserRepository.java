package com.dharmaraj.splitwise.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dharmaraj.splitwise.models.ExpenseUser;
import com.dharmaraj.splitwise.models.User;

@Repository
public interface ExpenseUserRepository extends JpaRepository<ExpenseUser, Long> {
    
    public List<ExpenseUser> findByUser(User user);
}
