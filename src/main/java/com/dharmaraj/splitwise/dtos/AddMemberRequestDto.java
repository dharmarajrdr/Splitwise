package com.dharmaraj.splitwise.dtos;

import lombok.Data;

@Data
public class AddMemberRequestDto {
    
    private long groupId;
    private long adminId;
    private long memberId;
}
