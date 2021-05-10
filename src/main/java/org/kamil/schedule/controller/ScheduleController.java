package org.kamil.schedule.controller;


import org.kamil.schedule.model.Lecture;
import org.kamil.schedule.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ScheduleController {

    @Autowired
    LectureRepository lectureRepository;


    @RequestMapping("/shedule")
    public String getSchedule(){
        return "schedule";
    }


    @RequestMapping("/schedule/times")
    public String getScheduleTimes(){
        return "schedule-times";
    }

    @RequestMapping("/lectures")
    public String getLectures(){
        return "lectures";
    }

    @RequestMapping("/subjects")
    public String getSubjects(ModelMap modelMap)
    {
        List<Lecture> subs = lectureRepository.findAll();
        modelMap.addAttribute("subs", subs);
        modelMap.addAttribute("subject", new Lecture());


        return "subjects";
    }

    @PostMapping("/subject/add")
    public String addSubject(Lecture lecture){

        lectureRepository.save(lecture);

        return "redirect:/subjects";

    }
}
