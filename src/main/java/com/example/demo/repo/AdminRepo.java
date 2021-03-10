package com.example.demo.repo;

import com.example.demo.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmailAndPassWord(String email, String passWord);
}
