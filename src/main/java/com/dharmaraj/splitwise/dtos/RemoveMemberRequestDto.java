package com.dharmaraj.splitwise.dtos;

import lombok.Data;

@Data
public class RemoveMemberRequestDto {

    private long groupId;
    private long adminId;
    private long memberId;
}
