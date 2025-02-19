package com.dharmaraj.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "expense_user")
public class ExpenseUser extends BaseModel {
    
    @ManyToOne  // If x and y are two attributes, then cardinality between x and xy will be @OneToMany
    private Expense expense;

    @ManyToOne  // If x and y are two attributes, then cardinality between x and xy will be @OneToMany
    private User user;
    
    private double amount;
    
    @Enumerated(EnumType.ORDINAL)
    private ExpenseUserType expenseUserType;
}
