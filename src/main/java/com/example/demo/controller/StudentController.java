package com.example.demo.controller;

import com.example.demo.dto.RegistrationDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.*;
import com.example.demo.service.CourseLevelService;
import com.example.demo.service.CourseRegistrationService;
import com.example.demo.service.CourseService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final CourseLevelService courseLevelService;
    private final CourseRegistrationService courseRegistrationService;

    @Autowired
    public StudentController(StudentService studentService, CourseService courseService, CourseLevelService courseLevelService, CourseRegistrationService courseRegistrationService) {

        this.studentService = studentService;
        this.courseService = courseService;
        this.courseLevelService = courseLevelService;
        this.courseRegistrationService = courseRegistrationService;

    }

    @GetMapping(path ="/signup")
    public String signUp(Model model){
        model.addAttribute("student", new Student());
        return "sign-up-form";
    }

    @PostMapping(path ="/signup")
    public String signUp(@ModelAttribute("student") Student student, Model model){
        studentService.saveStudent(student);
        return "redirect:/";
    }

    @GetMapping("/student-dashboard")
    public String viewHomePage( Model model, HttpSession session, UserDTO userDTO) throws Exception {
        Student student1 = (Student) session.getAttribute("student");
        if(student1 != null) {
            model.addAttribute("thisStudent", student1);
            return "student_dashboard";
        }
        return "redirect:/";
    }

    @GetMapping("/course-registration/{level}")
    public String registerCourse(Model model, HttpSession session, @PathVariable(value = "level") int level) {
        Student student1 = (Student) session.getAttribute("student");
        if(student1 != null) {
            CourseLevel courseLevel = courseLevelService.getSingleCourseLevel(level);
            List<Course> courses = courseService.getAllCoursesByStudentLevel(courseLevel);
            model.addAttribute("courseRegistration", new RegistrationDTO());
            model.addAttribute("student", student1);
            model.addAttribute("courses", courses);
            return "course-registration";
        }
        return "redirect:/";
    }

    @PostMapping("/student/{id}/assign-course")
    public String assignCourse(@PathVariable(value = "id") Long id, @ModelAttribute("courseRegistration") RegistrationDTO registrationDTO){
        courseRegistrationService.assignCourse(id, registrationDTO);
        return "redirect:/student-dashboard";
    }

    @GetMapping("/showUpdateBioForm/{id}")
    public String showUpdateBioForm(@PathVariable(value = "id") long id, HttpSession session, Model model){
       Object obj = session.getAttribute("student");
        if(obj == null) return "redirect:/";
        Student student = (Student) obj;
        model.addAttribute("student", student);
        model.addAttribute("newStudent", new Student());
        return "update-bio";
    }

    @PostMapping("/saveUpdatedBio/{id}")
    public String saveUpdatedBio(@ModelAttribute("newStudent") Student student,@PathVariable(value = "id") long id){
        studentService.updateStudent(student, id );
        return "redirect:/student-courses";

    }

    @GetMapping ("/selectLevel")
        public String selectLevel(Model model, HttpSession session){
        Student student = (Student) session.getAttribute("student");
        if(student == null) return "redirect:/";
        model.addAttribute("levels", courseLevelService.getCourseLevel());
        return "course-level";
    }

    @GetMapping("/view-details/{id}")
    public String viewStudentDetails(@PathVariable Long id,  Model model, HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        if(student == null) return "redirect:/";
        Student student1 = studentService.getStudentById(id);
        List<Course> courseList = courseService.getAllRegisteredCourses(id);
        model.addAttribute("student", student1);
        if (courseList.isEmpty()){
            model.addAttribute("noCourses", "No course(s) available");
        }else{
            model.addAttribute("courseList", courseList);
        }
        return "view-student-details";
    }

}
