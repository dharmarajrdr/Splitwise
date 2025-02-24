package com.dharmaraj.splitwise.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.dharmaraj.splitwise.dtos.*;
import com.dharmaraj.splitwise.exceptions.InvalidGroupException;
import com.dharmaraj.splitwise.exceptions.InvalidUserException;
import com.dharmaraj.splitwise.exceptions.UnAuthorizedAccessException;
import com.dharmaraj.splitwise.models.GroupMember;
import com.dharmaraj.splitwise.models.User;
import com.dharmaraj.splitwise.services.GroupService;

@Controller
public class GroupController {

    @Autowired
    private GroupService groupService;

    public AddMemberResponseDto addMember(AddMemberRequestDto requestDto) {
        AddMemberResponseDto addMemberResponseDto = new AddMemberResponseDto();
        try {
            long groupId = requestDto.getGroupId();
            long adminId = requestDto.getAdminId();
            long memberId = requestDto.getMemberId();
            GroupMember groupMember = this.groupService.addMember(groupId, adminId, memberId);
            addMemberResponseDto.setGroupMember(groupMember);
            addMemberResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (InvalidGroupException | InvalidUserException | UnAuthorizedAccessException e) {
            addMemberResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return addMemberResponseDto;
    }

    public RemoveMemberResponseDto removeMember(RemoveMemberRequestDto requestDto) {
        RemoveMemberResponseDto removeMemberResponseDto = new RemoveMemberResponseDto();
        try {
            long groupId = requestDto.getGroupId();
            long adminId = requestDto.getAdminId();
            long memberId = requestDto.getMemberId();
            this.groupService.removeMember(groupId, adminId, memberId);
            removeMemberResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (InvalidGroupException | InvalidUserException | UnAuthorizedAccessException e) {
            removeMemberResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return removeMemberResponseDto;
    }

    public FetchMembersResponseDto fetchMembers(FetchMembersRequestDto requestDto) {
        FetchMembersResponseDto fetchMembersResponseDto = new FetchMembersResponseDto();
        try {
            long groupId = requestDto.getGroupId();
            long memberId = requestDto.getMemberId();
            List<User> users = this.groupService.fetchAllMembers(groupId, memberId);
            fetchMembersResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            fetchMembersResponseDto.setMembers(users);
        } catch (InvalidGroupException | InvalidUserException | UnAuthorizedAccessException e) {
            fetchMembersResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return fetchMembersResponseDto;
    }
}
