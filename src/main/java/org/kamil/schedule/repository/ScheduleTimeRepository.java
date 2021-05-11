package org.kamil.schedule.repository;

import org.kamil.schedule.model.ScheduleTime;
import org.kamil.schedule.model.enums.ScheduleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleTimeRepository extends JpaRepository<ScheduleTime, Long> {

    List<ScheduleTime> findAllByType(ScheduleType scheduleType);
}
