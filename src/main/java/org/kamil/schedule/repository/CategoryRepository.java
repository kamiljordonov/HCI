package org.kamil.schedule.repository;


import org.kamil.schedule.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository("roleRepository")
public interface CategoryRepository extends JpaRepository<Category,Long> {
    public Category findByName(String name);
}
