package com.example.demo.repo;

import com.example.demo.model.Course;
import com.example.demo.model.CourseLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepo extends JpaRepository<Course, Long> {

    Optional<Course> findById(Long id);

    List<Course> findAllByCourseLevel(CourseLevel level);
    List<Course> findCoursesByCourseLevel(Long id);

}
