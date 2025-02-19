package com.dharmaraj.splitwise.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Expense extends BaseModel {
    
    private double amount;
    
    private String description;
    
    @ManyToOne
    private User createdBy;
    
    @OneToMany   // If x and y are two attributes, then cardinality between x and xy will be @OneToMany
    private List<ExpenseUser> expenseUsers;
    
    @Enumerated(EnumType.ORDINAL)   // Ordinal will store the value of the enum as Integer, not the entire enum
    private ExpenseType expenseType;
}

/**
 * 1 : 1
 * expense : createdBy
 * M : 1
 */