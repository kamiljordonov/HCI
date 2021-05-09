package org.kamil.schedule.repository;

import org.kamil.schedule.model.User;
import org.kamil.schedule.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
    public List<UserRole> findAllByUser(User user);
}
