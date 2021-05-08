package org.kamil.schedule.repository;

import org.kamil.schedule.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Long, Account> {

}
