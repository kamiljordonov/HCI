package org.kamil.schedule.repository;

import org.kamil.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Long, Schedule> {
}
