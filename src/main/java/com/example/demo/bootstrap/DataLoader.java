package com.example.demo.bootstrap;

import com.example.demo.model.Admin;
import com.example.demo.model.CourseLevel;
import com.example.demo.repo.AdminRepo;
import com.example.demo.repo.CourseLevelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader {
    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private CourseLevelRepo courseLevelRepo;


    @PostConstruct
    void init() {
        if (adminRepo.findByEmailAndPassWord("admin@admin.com", "12345").isPresent())
            return;
        Admin admin = new Admin();
        admin.setEmail("admin@admin.com");
        admin.setPassWord("12345");
        adminRepo.save(admin);

        if (courseLevelRepo.existsByLevel(100) || courseLevelRepo.existsByLevel(200)
        || courseLevelRepo.existsByLevel(300) || courseLevelRepo.existsByLevel(400))
            return;
        List<CourseLevel> levels = new ArrayList<>();
        CourseLevel courseLevel1 = new CourseLevel();
        courseLevel1.setLevel(100);
        levels.add(courseLevel1);
        CourseLevel courseLevel2 = new CourseLevel();
        courseLevel2.setLevel(200);
        levels.add(courseLevel2);
        CourseLevel courseLevel3 = new CourseLevel();
        courseLevel3.setLevel(300);
        levels.add(courseLevel3);
        CourseLevel courseLevel4 = new CourseLevel();
        courseLevel4.setLevel(400);
        levels.add(courseLevel4);
        courseLevelRepo.saveAll(levels);


    }
}
