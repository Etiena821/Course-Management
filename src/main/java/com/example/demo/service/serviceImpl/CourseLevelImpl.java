package com.example.demo.service.serviceImpl;

import com.example.demo.model.CourseLevel;
import com.example.demo.repo.CourseLevelRepo;
import com.example.demo.service.CourseLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseLevelImpl implements CourseLevelService {
    private final CourseLevelRepo courseLevelRepo;

    @Autowired
    public CourseLevelImpl(CourseLevelRepo courseLevelRepo) {
        this.courseLevelRepo = courseLevelRepo;
    }

    @Override
    public List<CourseLevel> getCourseLevel() {
        return courseLevelRepo.findAll();
    }

    @Override
    public CourseLevel getSingleCourseLevel(int level) {
        return courseLevelRepo.findByLevel(level);
    }

    @Override
    public List<CourseLevel> getAllLevel() {
        return courseLevelRepo.findAll();
    }
}
