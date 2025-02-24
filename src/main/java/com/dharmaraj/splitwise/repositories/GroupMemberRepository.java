package com.dharmaraj.splitwise.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dharmaraj.splitwise.models.Group;
import com.dharmaraj.splitwise.models.GroupMember;
import com.dharmaraj.splitwise.models.User;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {

    void deleteByGroupAndUser(Group group, User user);

    Optional<GroupMember> findByGroupAndUser(Group group, User user);

    List<GroupMember> findAllByGroup(Group group);

}