package com.example.demo.repo;

import com.example.demo.model.CourseLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseLevelRepo extends JpaRepository<CourseLevel, Long> {
    boolean existsByLevel(int level);

    CourseLevel findByLevel(int level);
}
