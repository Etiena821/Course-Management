package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.Admin;
import com.example.demo.model.Course;
import com.example.demo.model.CourseLevel;
import com.example.demo.model.Student;

import java.util.List;

public interface StudentService {

    Student saveStudent(Student student);
    Student updateStudent(Student student, Long id);
    Student getStudentById(Long id);
    List<Course> viewAllRegisteredCourses(long id, CourseLevel level);
    List<Student> viewAllRegisteredStudent();
    //void unRegisterStudentCourse(Student student, Course course);
    void registerCourse(Student student, Course courseToRegister);
    void changePassword(String password, String email);
    Student getStudentByEmail(String email);
    Student verifyStudentLogin(UserDTO userDTO) throws Exception;

}
