package org.kamil.schedule.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "student_lecture")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentLecture {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User student;

    @ManyToOne
    private Lecture lecture;
}
