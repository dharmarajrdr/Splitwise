package com.dharmaraj.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class GroupAdmin extends BaseModel {

    @OneToOne
    private Group group;
    
    @ManyToOne
    private User admin;
    
    @ManyToOne
    private User addedBy;
}

/**
 * 1 : 1
 * GroupAdmin : Group
 * 1 : 1
 */

/**
 * 1 : 1
 * GroupAdmin : Admin
 * M : 1
 */