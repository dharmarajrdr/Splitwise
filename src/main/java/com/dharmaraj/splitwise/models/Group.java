package com.dharmaraj.splitwise.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity(name = "groups")
public class Group extends BaseModel {
    
    private String name;

    private String description;
    
    @ManyToOne
    private User createdBy;
    
    @ManyToMany
    private List<User> members;
    
    @OneToMany
    private List<Expense> expenses;
}

/**
 * 1 : 1
 * group : created
 * M : 1
 */


/**
 * 1 : M
 * group : members
 * M : M
 */

/**
 * 1 : M
 * group : expense
 * 1 : 1
 */