package org.kamil.schedule.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ScheduleController {


    @RequestMapping("/shedule")
    public String getSchedule(){
        return "schedule";
    }
}
