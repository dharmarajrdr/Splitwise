package com.dharmaraj.splitwise.dtos;

import com.dharmaraj.splitwise.models.GroupMember;

import lombok.Data;

@Data
public class AddMemberResponseDto extends ResponseDto {
    
    private GroupMember groupMember;
}
