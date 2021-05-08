package org.kamil.schedule.repository;

import org.kamil.schedule.model.ScheduleTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleTimeRepository extends JpaRepository<Long, ScheduleTime> {
}
