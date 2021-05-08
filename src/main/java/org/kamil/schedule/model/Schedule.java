package org.kamil.schedule.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.DayOfWeek;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Schedule {


    @Id
    private Long id;

    @ManyToOne
    private Lecture lecture;

    @ManyToOne
    private ScheduleTime time;

    private DayOfWeek dayOfWeek;
}
