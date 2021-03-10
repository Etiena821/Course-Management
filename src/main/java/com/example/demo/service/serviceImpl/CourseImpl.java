package com.example.demo.service.serviceImpl;

import com.example.demo.model.Course;
import com.example.demo.model.CourseLevel;
import com.example.demo.model.CourseRegistration;
import com.example.demo.repo.CourseRepo;
import com.example.demo.service.CourseRegistrationService;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CourseImpl implements CourseService {

    private CourseRepo courseRepo;
    private final CourseRegistrationService courseRegistrationService;

    @Autowired
    public CourseImpl(CourseRepo courseRepo, CourseRegistrationService courseRegistrationService) {
        this.courseRepo = courseRepo;
        this.courseRegistrationService = courseRegistrationService;
    }

    @Override
    public List<Course> getAllRegisteredCourses(Long id)
    {
        List<CourseRegistration> courseRegistrations = courseRegistrationService.getStudentCourseRegistration(id);
        List<Course> courses = new ArrayList<>();
        courseRegistrations.forEach(courseRegistration -> {
            courses.add(courseRegistration.getCourse());
        });
        return courses;
    }

    @Override
    public List<Course> getAllCourses()
    {
        return courseRepo.findAll();

    }

    @Override
    public List<Course> getAllCoursesByLevelId(Long id) {
        return courseRepo.findCoursesByCourseLevel(id);
    }


    @Override
    public List<Course> getAllCoursesByStudentLevel(CourseLevel level) {
        return courseRepo.findAllByCourseLevel(level);
    }

    @Override
    public void saveRegisteredCourse(Course course) {
        Course course1 = new Course();
        course1.setCourseName(course.getCourseName());
        course1.setCourseLevel(course.getCourseLevel());
        this.courseRepo.save(course);
    }

    @Override
    public Course getRegisteredCourseById(long id) {
        return courseRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("No such course found"));
    }

    @Override
    public void deleteCourseById(long id) {
        this.deleteCourseById(id);
    }

}
