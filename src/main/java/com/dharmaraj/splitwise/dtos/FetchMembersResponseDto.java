package com.dharmaraj.splitwise.dtos;

import lombok.Data;

import java.util.List;

import com.dharmaraj.splitwise.models.User;

@Data
public class FetchMembersResponseDto extends ResponseDto {

    private List<User> members;
}
