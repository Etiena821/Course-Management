package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.CourseLevel;
import com.example.demo.model.Student;

import java.util.List;

public interface CourseService {
    List<Course> getAllRegisteredCourses(Long id);
    List<Course> getAllCoursesByStudentLevel(CourseLevel level);
    void saveRegisteredCourse(Course course);
    Course getRegisteredCourseById(long id);
    void deleteCourseById(long id);
    List<Course>getAllCourses();
    List<Course> getAllCoursesByLevelId( Long id);
}
