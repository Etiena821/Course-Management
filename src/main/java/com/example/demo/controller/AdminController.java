package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.*;
import com.example.demo.service.*;
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
public class AdminController {

    private final CourseService courseService;
    private final CourseLevelService courseLevelService;
    private final StudentService studentService;

    @Autowired
    public AdminController(CourseService courseService, CourseLevelService courseLevelService, StudentService studentService) {

        this.courseService = courseService;
        this.courseLevelService = courseLevelService;
        this.studentService = studentService;
    }

    @GetMapping("/course")
    public String viewHomePage(Model model, HttpSession session) throws Exception {
        Admin admin = (Admin) session.getAttribute("admin");
        if(admin != null) {
            model.addAttribute("studentList", studentService.viewAllRegisteredStudent());
            return "index";
        }
        return "redirect:/";
    }

    @GetMapping("/showNewCourseForm")
    public String showNewCourseForm(HttpSession session, Model model){
        Admin admin = (Admin) session.getAttribute("admin");
        if(admin == null) return "redirect:/";
        Course course = new Course();
        model.addAttribute("course", course);
        model.addAttribute("courseLevels", courseLevelService.getCourseLevel());
        return "new_course";
    }

    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute("course") Course course){

        int id = course.getCourseLevel().getLevel();
        course.setCourseLevel(courseLevelService.getSingleCourseLevel(id));
        courseService.saveRegisteredCourse(course);
        return "redirect:/course";
    }

    @GetMapping("/showNewStudentForm")
    public String showNewStudentForm(HttpSession session, Model model){
        Admin admin = (Admin) session.getAttribute("admin");
        if(admin == null) return "redirect:/";
        Student student = new Student();
        model.addAttribute("student", student);
        model.addAttribute("courseLevels", courseLevelService.getCourseLevel());
        return "new-student";
    }

    @PostMapping("/saveStudent")
    public String saveCourse(@ModelAttribute("student") Student student){
        int id = student.getCourseLevel().getLevel();
        student.setCourseLevel(courseLevelService.getSingleCourseLevel(id));
        studentService.saveStudent(student);
        return "redirect:/course";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if(admin == null) return "redirect:/";
        Course course = courseService.getRegisteredCourseById(id);
        model.addAttribute("course", course);
        return "update_course";
    }

    @GetMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable (value = "id") long id) {
        courseService.deleteCourseById(id);
        return "redirect:/courses";
    }


    @GetMapping("/view-course-list")
    public String viewRegisteredCourses(Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if(admin == null) return "redirect:/";
        List<Course> courseList = courseService.getAllCourses();
            model.addAttribute("courseList", courseList);
        return "courses-list";
    }

}
