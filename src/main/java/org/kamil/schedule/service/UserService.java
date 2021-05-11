package org.kamil.schedule.service;



import org.kamil.schedule.model.Schedule;
import org.kamil.schedule.model.StudentLecture;
import org.kamil.schedule.model.User;

import java.time.DayOfWeek;
import java.util.List;

public interface UserService {
    public User findById(Long id);
    public User findUserByUsername(String username);
    public User findUserByEmail(String email);
    public void save(User user);

    Long findRoleIdByUsername(String username);

    List<User> findTeachers();

    List<Schedule> findTeacherSchedule(DayOfWeek dayOfWeek);

    List<Schedule> findStudentSchedule(DayOfWeek dayOfWeek);

    List<StudentLecture> findStudentCourses();

}