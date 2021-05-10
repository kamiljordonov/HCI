package org.kamil.schedule.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.DayOfWeek;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Schedule {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Lecture lecture;

    @ManyToOne
    private ScheduleTime time;

    private DayOfWeek dayOfWeek;
}
