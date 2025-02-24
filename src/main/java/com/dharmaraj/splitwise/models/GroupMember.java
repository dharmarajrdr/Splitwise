package com.dharmaraj.splitwise.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class GroupMember extends BaseModel {

    @ManyToOne
    private Group group;

    @ManyToOne
    private User user;

    @ManyToOne
    private User addedBy;

    private Date addedAt;
}

/**
 * 1 : 1
 * GroupMember : Group
 * M : 1
 */

/**
 * 1 : 1
 * GroupMember : User
 * M : 1
 */
