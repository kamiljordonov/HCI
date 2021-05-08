package org.kamil.schedule.repository;

import org.kamil.schedule.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Long, Role> {
}
