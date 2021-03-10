package com.example.demo.controller;


import com.example.demo.dto.UserDTO;
import com.example.demo.model.Admin;
import com.example.demo.model.Student;
import com.example.demo.service.AdminService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpSession;

@Controller
public class AccountController {

    private final AdminService adminService;
    private final StudentService studentService;

    @Autowired
    public AccountController(AdminService adminService, StudentService studentService) {
        this.adminService = adminService;
        this.studentService = studentService;
    }

    // Login form
    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("admin", new UserDTO());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("admin") UserDTO userDTO, HttpSession session,Model model ) throws Exception {
        Admin admin = adminService.verifyLogin(userDTO);
        Student student = studentService.verifyStudentLogin(userDTO);

        if(admin == null && student == null){
            model.addAttribute("invalid", "User does not exist");
            return "redirect:/";
        }else if(admin != null){
            session.setAttribute("admin",admin);
            return "redirect:/course";
        }else {
            session.setAttribute("student", student);
            return "redirect:/student-dashboard";
        }
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session){
        if (session != null) session.invalidate();
        return "redirect:/";
    }



}
