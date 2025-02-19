package com.dharmaraj.splitwise.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dharmaraj.splitwise.dtos.ResponseStatus;
import com.dharmaraj.splitwise.dtos.SettleupGroupRequestDto;
import com.dharmaraj.splitwise.dtos.SettleupGroupResponseDto;
import com.dharmaraj.splitwise.dtos.SettleupUserRequestDto;
import com.dharmaraj.splitwise.dtos.SettleupUserResponseDto;
import com.dharmaraj.splitwise.models.Expense;
import com.dharmaraj.splitwise.services.SettleupService;

@RestController
@RequestMapping("/api/splitwise")
public class SettleupController {

    @Autowired
    private SettleupService settleupService;
    
    @PostMapping("/settleup/user")
    public ResponseEntity<SettleupUserResponseDto> settleupUser(@RequestBody SettleupUserRequestDto settleupUserRequestDto) {

        SettleupUserResponseDto settleupUserResponseDto = new SettleupUserResponseDto();
        List<Expense> expenses = this.settleupService.settleupUser(settleupUserRequestDto.getUserId());
        settleupUserResponseDto.setExpenses(expenses);
        settleupUserResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        return ResponseEntity.status(200).body(settleupUserResponseDto);
    }

    @PostMapping("/settleup/group")
    public ResponseEntity<SettleupGroupResponseDto> settleupGroup(@RequestBody SettleupGroupRequestDto settleupGroupRequestDto) {

        SettleupGroupResponseDto settleupGroupResponseDto = new SettleupGroupResponseDto();
        List<Expense> expenses = this.settleupService.settleupGroup(settleupGroupRequestDto.getGroupId());
        settleupGroupResponseDto.setExpenses(expenses);
        settleupGroupResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        return ResponseEntity.status(200).body(settleupGroupResponseDto);
    }
}
