package com.dharmaraj.splitwise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.dharmaraj.splitwise.models.Group;
import com.dharmaraj.splitwise.models.GroupAdmin;
import com.dharmaraj.splitwise.models.User;

@Repository
public interface GroupAdminRepository extends JpaRepository<GroupAdmin, Long> {

    Optional<GroupAdmin> findByGroupAndAdmin(Group group, User user);

    List<GroupAdmin> findAllByGroup(Group group);

    void deleteByGroup(Group group);

}
