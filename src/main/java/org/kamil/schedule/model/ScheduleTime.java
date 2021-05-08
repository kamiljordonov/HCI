package org.kamil.schedule.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.kamil.schedule.model.enums.ScheduleType;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScheduleTime {

    @Id
    private Long Id;

    private ScheduleType type;


}
