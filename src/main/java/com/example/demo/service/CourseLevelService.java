package com.example.demo.service;

import com.example.demo.model.CourseLevel;

import java.util.List;

public interface CourseLevelService {
    List<CourseLevel> getCourseLevel();
    CourseLevel getSingleCourseLevel(int level);
    List<CourseLevel> getAllLevel();
}
