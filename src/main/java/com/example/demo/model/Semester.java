package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstSemester;
    private String secondSemester;

    @OneToMany
    private List<Course> courses;
}
