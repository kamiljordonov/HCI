package org.kamil.schedule.service;


import org.kamil.schedule.model.*;
import org.kamil.schedule.repository.ScheduleRepository;
import org.kamil.schedule.repository.StudentLectureRepository;
import org.kamil.schedule.repository.UserRepository;
import org.kamil.schedule.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private StudentLectureRepository studentLectureRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Long findRoleIdByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        UserRole userRole = userRoleRepository.findAllByUser(user).get(0);

        Long roleId = userRole.getRole().getId();

        return roleId;

    }

    @Override
    public List<User> findTeachers() {

        List<UserRole> userRoles = userRoleRepository.findAllByRoleId(Long.valueOf(2));

        List<User> teachers = new ArrayList<>();

        for(UserRole userRole: userRoles){
            teachers.add(userRole.getUser());
        }

        return teachers;

    }

    @Override
    public List<Schedule> findTeacherSchedule(DayOfWeek dayOfWeek) {
        List<Schedule> scheduleList = scheduleRepository.findAllByDayOfWeek(dayOfWeek);

        List<Schedule> schedules = new ArrayList<>();

        Long userId = userRepository.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId();

        for(Schedule schedule: scheduleList){
            if(userId.equals(schedule.getLecture().getTeacher().getId())){

                schedules.add(schedule);

            }
        }

        return schedules;

    }

    @Override
    public List<Schedule> findStudentSchedule(DayOfWeek dayOfWeek) {

        List<StudentLecture> studentLectures = findStudentCourses();

        List<Schedule> schedules = new ArrayList<>();

        for(StudentLecture studentLecture: studentLectures){

            schedules.addAll(scheduleRepository.findByDayOfWeekAndLectureId(dayOfWeek, studentLecture.getLecture().getId()));


        }

        return schedules;
    }

    @Override
    public List<StudentLecture> findStudentCourses() {
        User user = findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        List<StudentLecture> studentLectures = studentLectureRepository.findByStudentId(user.getId());

        return studentLectures;

    }
}