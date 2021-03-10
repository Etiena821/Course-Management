package com.example.demo.repo;

import com.example.demo.model.CourseRegistration;
import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRegistrationRepo extends JpaRepository<CourseRegistration, Long> {
    List<CourseRegistration> findAllByStudent(Student student);
    List<CourseRegistration> findAll();

}
