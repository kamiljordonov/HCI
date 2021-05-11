package org.kamil.schedule.controller;


import org.dom4j.rule.Mode;
import org.kamil.schedule.model.*;
import org.kamil.schedule.model.enums.ScheduleType;
import org.kamil.schedule.payload.LectureDto;
import org.kamil.schedule.payload.ScheduleDto;
import org.kamil.schedule.payload.ScheduleTimeDto;
import org.kamil.schedule.repository.LectureRepository;
import org.kamil.schedule.repository.ScheduleRepository;
import org.kamil.schedule.repository.ScheduleTimeRepository;
import org.kamil.schedule.repository.StudentLectureRepository;
import org.kamil.schedule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.List;

@Controller
public class ScheduleController {

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    UserService userService;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    ScheduleTimeRepository scheduleTimeRepository;

    @Autowired
    StudentLectureRepository studentLectureRepository;


    @RequestMapping("/shedule")
    public String getSchedule(Model model){


        List<Schedule> monday = scheduleRepository.findAllByDayOfWeek(DayOfWeek.MONDAY);
        List<Schedule> tuesday = scheduleRepository.findAllByDayOfWeek(DayOfWeek.TUESDAY);
        List<Schedule> wednesday = scheduleRepository.findAllByDayOfWeek(DayOfWeek.WEDNESDAY);
        List<Schedule> thursday = scheduleRepository.findAllByDayOfWeek(DayOfWeek.THURSDAY);
        List<Schedule> friday = scheduleRepository.findAllByDayOfWeek(DayOfWeek.FRIDAY);
        List<Schedule> saturday = scheduleRepository.findAllByDayOfWeek(DayOfWeek.SATURDAY);



        model.addAttribute("monday", monday);
        model.addAttribute("tuesday", tuesday);
        model.addAttribute("wednesday", wednesday);
        model.addAttribute("thursday", thursday);
        model.addAttribute("friday", friday);
        model.addAttribute("saturday", saturday);

        model.addAttribute("schedule", new ScheduleDto());

        model.addAttribute("days", DayOfWeek.values());

        List<Lecture> lectures = lectureRepository.findAll();

        List<ScheduleTime> scheduleTimes = scheduleTimeRepository.findAllByType(ScheduleType.Lecture);

        model.addAttribute("scheduleTimes", scheduleTimes);
        model.addAttribute("lectures", lectures);

        return "schedule";
    }


    @RequestMapping("/teacher/schedule")
    public String getTeacherSchedule(Model model){
        List<Schedule> monday = userService.findTeacherSchedule(DayOfWeek.MONDAY);
        List<Schedule> tuesday = userService.findTeacherSchedule(DayOfWeek.TUESDAY);
        List<Schedule> wednesday = userService.findTeacherSchedule(DayOfWeek.WEDNESDAY);
        List<Schedule> thursday = userService.findTeacherSchedule(DayOfWeek.THURSDAY);
        List<Schedule> friday = userService.findTeacherSchedule(DayOfWeek.FRIDAY);
        List<Schedule> saturday = userService.findTeacherSchedule(DayOfWeek.SATURDAY);



        model.addAttribute("monday", monday);
        model.addAttribute("tuesday", tuesday);
        model.addAttribute("wednesday", wednesday);
        model.addAttribute("thursday", thursday);
        model.addAttribute("friday", friday);
        model.addAttribute("saturday", saturday);




        return "teacher-schedule";
    }

    @RequestMapping("/student/schedule")
    public String getStudentSchedule(Model model){
        List<Schedule> monday = userService.findStudentSchedule(DayOfWeek.MONDAY);
        List<Schedule> tuesday = userService.findStudentSchedule(DayOfWeek.TUESDAY);
        List<Schedule> wednesday = userService.findStudentSchedule(DayOfWeek.WEDNESDAY);
        List<Schedule> thursday = userService.findStudentSchedule(DayOfWeek.THURSDAY);
        List<Schedule> friday = userService.findStudentSchedule(DayOfWeek.FRIDAY);
        List<Schedule> saturday = userService.findStudentSchedule(DayOfWeek.SATURDAY);



        model.addAttribute("monday", monday);
        model.addAttribute("tuesday", tuesday);
        model.addAttribute("wednesday", wednesday);
        model.addAttribute("thursday", thursday);
        model.addAttribute("friday", friday);
        model.addAttribute("saturday", saturday);




        return "student-schedule";
    }


    @RequestMapping("/teacher/lectures")
    public String getTeacherLectures(Model model){

        User teacher = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        List<Lecture> lectures = lectureRepository.findByTeacherId(teacher.getId());

        model.addAttribute("lectures", lectures);

        return "teacher-lectures";

    }



    @PostMapping("/schedule/add")
    public String addSchedule(ScheduleDto scheduleDto){

        Schedule schedule = new Schedule();
        Lecture lecture = lectureRepository.findById(scheduleDto.getLecture()).get();
        ScheduleTime scheduleTime = scheduleTimeRepository.findById(scheduleDto.getScheduleTime()).get();

        schedule.setLecture(lecture);
        schedule.setTime(scheduleTime);
        schedule.setDayOfWeek(scheduleDto.getDayOfWeek());

        scheduleRepository.save(schedule);

        return "redirect:/shedule";

    }

    @RequestMapping("/schedule/times")
    public String getScheduleTimes(Model model){

        List<ScheduleTime> scheduleTimes = scheduleTimeRepository.findAll();


        model.addAttribute("scheduleTimes", scheduleTimes);
        model.addAttribute("scheduleTime", new ScheduleTimeDto());
        model.addAttribute("types", ScheduleType.values());

        return "schedule-times";
    }


    private Time getTime(String s){
        int hour = Integer.parseInt(s.substring(0, 2));
        int minute = Integer.parseInt(s.substring(3));

        return new Time(hour, minute, 0);
    }

    @PostMapping("/schedule-time/add")
    public String addScheduleTime(ScheduleTimeDto scheduleTimeDto) {

        ScheduleTime scheduleTime = new ScheduleTime();


        scheduleTime.setStart(getTime(scheduleTimeDto.getStart()));
        scheduleTime.setFinish(getTime(scheduleTimeDto.getFinish()));
        scheduleTime.setType(scheduleTimeDto.getType());


        scheduleTimeRepository.save(scheduleTime);


        return "redirect:/schedule/times";
    }

    @RequestMapping("/student/lectures")
    public String getLectures(Model model){

        List<StudentLecture> studentLectures = userService.findStudentCourses();

        List<Lecture> lectures = lectureRepository.findAll();

        model.addAttribute("lectures", studentLectures);
        model.addAttribute("courses", lectures);

        return "lectures";
    }

    @PostMapping("/course/add")
    public String addCourse(@RequestParam(name = "lecture") Long lecture){

        User user = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        StudentLecture studentLecture = new StudentLecture();

        Lecture lecture1 = lectureRepository.findById(lecture).get();

        studentLecture.setStudent(user);
        studentLecture.setLecture(lecture1);

        studentLectureRepository.save(studentLecture);


        return "redirect:/student/lectures";

    }

    @RequestMapping("/subjects")
    public String getSubjects(Model model)
    {
        List<User> teachers = userService.findTeachers();

        model.addAttribute("teachers", teachers);


        List<Lecture> lectures = lectureRepository.findAll();
        model.addAttribute("lectures", lectures);
        model.addAttribute("subject", new LectureDto());


        return "subjects";
    }

    @PostMapping("/subject/add")
    public String addSubject(LectureDto lecture){

        Lecture lecture1 = new Lecture();

        lecture1.setCode(lecture.getCode());
        lecture1.setName(lecture.getName());

        User teacher = userService.findById(lecture.getTeacher());

        lecture1.setTeacher(teacher);

        lectureRepository.save(lecture1);

        return "redirect:/subjects";

    }
}
