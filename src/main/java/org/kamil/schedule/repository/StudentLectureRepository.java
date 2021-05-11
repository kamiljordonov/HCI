package org.kamil.schedule.repository;


import org.kamil.schedule.model.StudentLecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentLectureRepository extends JpaRepository<StudentLecture, Long> {


    List<StudentLecture> findByStudentId(Long studentId);
 }
