package com.example.demo.service;

import com.example.demo.dto.RegistrationDTO;
import com.example.demo.model.Course;
import com.example.demo.model.CourseRegistration;

import java.util.List;

public interface CourseRegistrationService {
    List<CourseRegistration> getStudentCourseRegistration(Long id);
    void assignCourse(Long id, RegistrationDTO registrationDTO);
}
