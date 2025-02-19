package com.dharmaraj.splitwise.strategies;

import java.util.List;

import com.dharmaraj.splitwise.models.Expense;

public interface SettleupStrategy {
    
    public List<Expense> settleUp(List<Expense> expenses);
}
