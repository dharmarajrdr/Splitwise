package com.dharmaraj.splitwise.dtos;

import java.util.List;

import com.dharmaraj.splitwise.models.Expense;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettleupUserResponseDto extends ResponseDto {
    
    private List<Expense> expenses;
}
