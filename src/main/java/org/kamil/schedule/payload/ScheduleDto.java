package org.kamil.schedule.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDto
{

    private Long lecture;

    private Long scheduleTime;

    private DayOfWeek dayOfWeek;
}
