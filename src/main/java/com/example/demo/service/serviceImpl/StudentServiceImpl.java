package com.example.demo.service.serviceImpl;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.Course;
import com.example.demo.model.CourseLevel;
import com.example.demo.model.CourseRegistration;
import com.example.demo.model.Student;
import com.example.demo.repo.CourseRegistrationRepo;
import com.example.demo.repo.CourseRepo;
import com.example.demo.repo.StudentRepo;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

   private final StudentRepo studentRepo;
   private final CourseRepo courseRepo;
   private final CourseRegistrationRepo courseRegistrationRepo;

   @Autowired
    public StudentServiceImpl(StudentRepo studentRepo, CourseRepo courseRepo, CourseRegistrationRepo courseRegistrationRepo) {
        this.studentRepo = studentRepo;
       this.courseRepo = courseRepo;
       this.courseRegistrationRepo = courseRegistrationRepo;
   }

    @Override
    public void changePassword(String password, String email){
        Student student = studentRepo.findByEmail(email);
        student.setPassword(password);
        studentRepo.save(student);
    }

    @Override
    public Student getStudentByEmail(String email) {
        return studentRepo.findByEmail(email);
    }

    @Override
    public Student verifyStudentLogin(UserDTO userDTO) throws Exception {
        Optional<Student> student = studentRepo.findByEmailAndPassword(userDTO.getEmail(), userDTO.getPassWord());
        if (student.isPresent()) {
            return student.get();
        }
        return null;
    }


    @Override
    public Student saveStudent(Student student){
       return studentRepo.save(student);
    }

    @Override
    public Student updateStudent(Student student, Long id) {
     Student student1 =   studentRepo.findById(id).get();
       student1.setFirstName(student.getFirstName());
       student1.setLastName(student.getLastName());
       student1.setUserName(student.getUserName());
       student1.setPassword(student.getPassword());
       return studentRepo.save(student1);
    }

    @Override
    public Student getStudentById(Long id) {
        Optional<Student> student = studentRepo.findById(id);
        if (student.isPresent()) return student.get();
        return null;
    }

    @Override
    public List<Course> viewAllRegisteredCourses(long id, CourseLevel level) {
        return courseRepo.findAllByCourseLevel(level);
    }

    @Override
    public List<Student> viewAllRegisteredStudent() {
        return studentRepo.findAll();
    }

    @Override
    public void registerCourse(Student student, Course courseToRegister) {
        CourseRegistration courseRegistration = new CourseRegistration();
        courseRegistration.setCourse(courseToRegister);
        courseRegistration.setStudent(student);
        courseRegistrationRepo.save(courseRegistration);
    }
//
//    @Override
//    public List<Course> viewAllRegisteredCourses(long id){
//        return courseRepo.findAllById(id);
//        //return null;
//    }

//    @Override
//    public void unRegisterStudentCourse(Student student, Course course){
////        Set<Course> currentCourses = student.getCourses();
////        currentCourses.remove(course);
//        this.saveStudent(student);
//    }
}
