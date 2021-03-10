package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course extends BaseModel{

    private String courseName;

    @ManyToOne
    private CourseLevel courseLevel;

    @OneToMany(mappedBy = "course")
    private Set<CourseRegistration> registrations;
}
