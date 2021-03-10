package com.example.demo.service.serviceImpl;

import com.example.demo.dto.RegistrationDTO;
import com.example.demo.model.Course;
import com.example.demo.model.CourseRegistration;
import com.example.demo.model.Student;
import com.example.demo.repo.CourseLevelRepo;
import com.example.demo.repo.CourseRegistrationRepo;
import com.example.demo.repo.CourseRepo;
import com.example.demo.repo.StudentRepo;
import com.example.demo.service.CourseRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseRegistrationImpl implements CourseRegistrationService {

    @Autowired
    private CourseRegistrationRepo courseRegistrationRepo;
    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Override
    public List<CourseRegistration> getStudentCourseRegistration(Long id) {
        Student student = studentRepo.findById(id).get();
        return courseRegistrationRepo.findAllByStudent(student);
    }

    @Override
    public void assignCourse(Long id, RegistrationDTO registrationDTO) {
        Student student = studentRepo.findById(id).get();
        Course course = courseRepo.findById(registrationDTO.getCourseId()).get();
        CourseRegistration courseRegistration1 = new CourseRegistration();
        courseRegistration1.setStudent(student);
        courseRegistration1.setCourse(course);
        courseRegistrationRepo.save(courseRegistration1);
    }
}
