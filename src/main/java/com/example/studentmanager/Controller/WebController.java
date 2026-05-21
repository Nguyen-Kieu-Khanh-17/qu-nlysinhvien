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
        // Giao diện mới chạy theo kiểu frontend -> gọi trực tiếp REST API.
        // Không cần nhúng dữ liệu students vào Thymeleaf.
        return "index"; // src/main/resources/templates/index.html
    }

}
