package org.kamil.schedule.repository;


import org.kamil.schedule.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role,Long> {
    public Role findByName(String name);
}
