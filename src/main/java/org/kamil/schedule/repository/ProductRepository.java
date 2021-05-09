package org.kamil.schedule.repository;


import org.kamil.schedule.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository("roleRepository")
public interface ProductRepository extends JpaRepository<Product,Long> {
    public Product findByTitle(String title);
}
