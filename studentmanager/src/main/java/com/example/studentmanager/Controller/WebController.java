package com.example.studentmanager.Controller;

import com.example.studentmanager.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/student-manager")
    public String studentManager(Model model) {
        model.addAttribute("students", studentService.getAll());
        return "students"; // refers to src/main/resources/templates/students.html
    }
}
