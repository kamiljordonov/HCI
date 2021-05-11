package org.kamil.schedule.repository;

import org.kamil.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByDayOfWeek(DayOfWeek dayOfWeek);

    List<Schedule> findByDayOfWeekAndLectureId(DayOfWeek dayOfWeek, Long lectureId);

}
