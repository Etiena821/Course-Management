package com.example.demo.repo;


import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface StudentRepo extends JpaRepository<Student, Long> {

    Optional<Student> findById(Long id);
   Optional<Student> findByEmailAndPassword(String email,String passWord);
    Student findByEmail(String email);
}
