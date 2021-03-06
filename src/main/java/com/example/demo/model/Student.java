package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student extends BaseModel{

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    @ManyToOne
    private CourseLevel courseLevel;

    @Email()
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "student")
    private Set<CourseRegistration> registrations;
}
