package org.kamil.schedule.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kamil.schedule.model.enums.ScheduleType;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleTimeDto {

    private String start;

    private String finish;

    private ScheduleType type;
}
