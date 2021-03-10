package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.Email;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Admin extends BaseModel {

    private String firstName;
    private String lastName;
    private String userName;
    private String passWord;
    @Email
    private String email;
}
