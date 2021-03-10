package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Course {

    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String code;
    private String unit;
    private String description;

    @ManyToOne
    private Semester semester;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Lecturer lecturer;

}
