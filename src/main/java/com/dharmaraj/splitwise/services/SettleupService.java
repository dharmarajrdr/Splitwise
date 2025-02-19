package com.dharmaraj.splitwise.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.dharmaraj.splitwise.exceptions.UserNotFound;
import com.dharmaraj.splitwise.models.Expense;
import com.dharmaraj.splitwise.models.ExpenseUser;
import com.dharmaraj.splitwise.models.User;
import com.dharmaraj.splitwise.repositories.ExpenseUserRepository;
import com.dharmaraj.splitwise.repositories.UserRepository;
import com.dharmaraj.splitwise.strategies.SettleupStrategy;

@Service
public class SettleupService {

    private UserRepository userRepository;
    private ExpenseUserRepository expenseUserRepository;
    private SettleupStrategy settleupStrategy;

    public SettleupService(UserRepository userRepository, ExpenseUserRepository expenseUserRepository, SettleupStrategy settleupStrategy) {
        this.userRepository = userRepository;
        this.expenseUserRepository = expenseUserRepository;
        this.settleupStrategy = settleupStrategy;
    }

    private User findUserById(Long userId)  {
        
        Optional<User> optionalUser = this.userRepository.findUserById(userId);
        if(optionalUser.isEmpty()) {
            throw new UserNotFound(userId);
        }
        return optionalUser.get();
    }
    
    public List<Expense> settleupUser(Long userId) throws UserNotFound {

        User user = findUserById(userId);
        
        // Fetch all the expenses in which the current user is present
        List<ExpenseUser> expenseUsers = this.expenseUserRepository.findByUser(user);

        Set<Expense> expensesToSettle = new HashSet<>();
        for(ExpenseUser expenseUser: expenseUsers) {
            expensesToSettle.add(expenseUser.getExpense());
        }

        List<Expense> transactions = this.settleupStrategy.settleUp(expensesToSettle.stream().toList());

        List<Expense> transactionsOfUser = new ArrayList<>();

        for(Expense expense: transactions) {
            for(ExpenseUser expenseUser: expense.getExpenseUsers()) {
                if(expenseUser.getUser().equals(user)) {
                    transactionsOfUser.add(expense);
                    break;
                }
            }
        }

        return transactionsOfUser;
    }

    public List<Expense> settleupGroup(Long groupId) {

        return null;
    }
}
