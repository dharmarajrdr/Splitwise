package com.dharmaraj.splitwise.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dharmaraj.splitwise.exceptions.InvalidGroupException;
import com.dharmaraj.splitwise.exceptions.InvalidUserException;
import com.dharmaraj.splitwise.exceptions.UnAuthorizedAccessException;
import com.dharmaraj.splitwise.models.Group;
import com.dharmaraj.splitwise.models.GroupAdmin;
import com.dharmaraj.splitwise.models.GroupMember;
import com.dharmaraj.splitwise.models.User;
import com.dharmaraj.splitwise.repositories.GroupAdminRepository;
import com.dharmaraj.splitwise.repositories.GroupMemberRepository;
import com.dharmaraj.splitwise.repositories.GroupRepository;
import com.dharmaraj.splitwise.repositories.UserRepository;

@Service
public class GroupService {
    
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final GroupAdminRepository groupAdminRepository;
    private final GroupMemberRepository groupMemberRepository;

    public GroupService(GroupRepository groupRepository, UserRepository userRepository, GroupAdminRepository groupAdminRepository, GroupMemberRepository groupMemberRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.groupAdminRepository = groupAdminRepository;
        this.groupMemberRepository = groupMemberRepository;
    }


    public GroupMember addMember(long groupId, long adminId, long userId) throws InvalidGroupException, InvalidUserException, UnAuthorizedAccessException {

        Group group = this.groupRepository.findById(groupId).orElseThrow(() -> new InvalidGroupException("Group with id " + groupId +" does not exist."));
        User user = this.userRepository.findById(userId).orElseThrow(() -> new InvalidUserException("User with id " + userId +" does not exist."));
        User admin = this.userRepository.findById(adminId).orElseThrow(() -> new InvalidUserException("User with id " + adminId +" does not exist."));
        this.groupAdminRepository.findByGroupAndAdmin(group, admin).orElseThrow(() -> new UnAuthorizedAccessException("User with id " + adminId + " is not a admin of the group."));

        Optional<GroupMember> optionalGroupMember = this.groupMemberRepository.findByGroupAndUser(group, user);
        
        if(optionalGroupMember.isPresent()) {
            throw new InvalidUserException("Given user already exist in the group.");
        }

        GroupMember groupMember = new GroupMember();
        groupMember.setUser(user);
        groupMember.setGroup(group);
        groupMember.setAddedBy(admin);
        groupMember.setAddedAt(new Date());

        this.groupMemberRepository.save(groupMember);

        return groupMember;
    }

    @Transactional
    public void removeMember(long groupId, long adminId, long userId) throws InvalidGroupException, UnAuthorizedAccessException, InvalidUserException {
    
        Group group = this.groupRepository.findById(groupId).orElseThrow(() -> new InvalidGroupException("Group with id " + groupId +" does not exist."));
        User user = this.userRepository.findById(userId).orElseThrow(() -> new InvalidUserException("User with id " + userId +" does not exist."));
        User admin = this.userRepository.findById(adminId).orElseThrow(() -> new InvalidUserException("User with id " + adminId +" does not exist."));
        this.groupAdminRepository.findByGroupAndAdmin(group, admin).orElseThrow(() -> new UnAuthorizedAccessException("User with id " + adminId + " is not a admin of the group."));
        this.groupMemberRepository.findByGroupAndUser(group, user).orElseThrow(() -> new InvalidGroupException("User not found."));

        this.groupMemberRepository.deleteByGroupAndUser(group, user);
    }

    public List<User> fetchAllMembers(long groupId, long userId) throws InvalidGroupException, UnAuthorizedAccessException, InvalidUserException {

        Group group = this.groupRepository.findById(groupId).orElseThrow(() -> new InvalidGroupException("Group with id " + groupId +" does not exist."));
        User user = this.userRepository.findById(userId).orElseThrow(() -> new InvalidUserException("User with id " + userId +" does not exist."));
        this.groupMemberRepository.findByGroupAndUser(group, user).orElseThrow(() -> new UnAuthorizedAccessException("Permission denied"));

        List<GroupMember> groupMembers = this.groupMemberRepository.findAllByGroup(group);
        List<GroupAdmin> groupAdmins = this.groupAdminRepository.findAllByGroup(group);
        
        List<User> members = new ArrayList<>();
        for(GroupMember groupMember: groupMembers) {
            members.add(groupMember.getUser());
        }
        for(GroupAdmin groupAdmin: groupAdmins) {
            members.add(groupAdmin.getAdmin());
        }

        return members;
    }

    public Group createGroup(String groupName, String description, long userId) throws InvalidUserException {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new InvalidUserException("User with id " + userId +" does not exist."));

        Group group = new Group();
        group.setName(groupName);
        group.setDescription(description);
        group.setCreatedAt(new Date());

        this.groupRepository.save(group);
        
        GroupAdmin groupAdmin = new GroupAdmin();
        groupAdmin.setGroup(group);
        groupAdmin.setAddedBy(user);
        groupAdmin.setAddedBy(user);

        this.groupAdminRepository.save(groupAdmin);

        return group;
    }

    @Transactional
    public void deleteGroup(long groupId, long userId) throws InvalidGroupException, UnAuthorizedAccessException, InvalidUserException {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new InvalidUserException("User with id " + userId +" does not exist."));
        Group group = this.groupRepository.findById(groupId).orElseThrow(() -> new InvalidGroupException("Group with id " + groupId +" does not exist."));

        this.groupAdminRepository.findByGroupAndAdmin(group, user).orElseThrow(() -> new UnAuthorizedAccessException("User with id " + userId + " is not a admin of the group."));

        this.groupAdminRepository.deleteByGroup(group);

        this.groupMemberRepository.deleteByGroup(group);

        this.groupRepository.delete(group);
    }

}
