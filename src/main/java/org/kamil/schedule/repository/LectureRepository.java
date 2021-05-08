package org.kamil.schedule.repository;

import org.kamil.schedule.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Long, Lecture> {
}
