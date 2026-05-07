// StudentApiController.java
package com.example.studentmanager.Controller;

import com.example.studentmanager.Entity.Student;
import com.example.studentmanager.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentApiController {

    @Autowired
    private StudentService service;

    // Yêu cầu 5: Lấy danh sách - Get All
    @GetMapping
    public List<Student> getAll() {
        return service.getAll();
    }

    // Yêu cầu 4: Lấy sinh viên theo ID
    @GetMapping("/{id}")
    public Student getById(@PathVariable int id) {
        return service.getById(id);
    }

    // Yêu cầu 3: Tìm kiếm sinh viên theo tên
    @GetMapping("/search")
    public List<Student> search(@RequestParam String keyword) {
        return service.search(keyword);
    }

    @Autowired
    private com.example.studentmanager.Service.FileService fileService;

    // Yêu cầu 1: Thêm mới sinh viên
    @PostMapping
    public Student add(@ModelAttribute Student student, @RequestParam(value = "avatarFile", required = false) org.springframework.web.multipart.MultipartFile avatarFile) {
        try {
            if (avatarFile != null && !avatarFile.isEmpty()) {
                String avatarUrl = fileService.saveAvatar(avatarFile);
                student.setAvatar(avatarUrl);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return service.save(student);
    }

    // Yêu cầu 6: Cập nhật sinh viên
    @PostMapping("/update/{id}")
    public Student update(@PathVariable int id, @ModelAttribute Student student, @RequestParam(value = "avatarFile", required = false) org.springframework.web.multipart.MultipartFile avatarFile) {
        student.setId(id);
        try {
            if (avatarFile != null && !avatarFile.isEmpty()) {
                String avatarUrl = fileService.saveAvatar(avatarFile);
                student.setAvatar(avatarUrl);
            } else {
                // If no new avatar is uploaded, keep the old one
                Student existingStudent = service.getById(id);
                if (existingStudent != null) {
                    student.setAvatar(existingStudent.getAvatar());
                }
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return service.save(student);
    }

    @Autowired
    private com.example.studentmanager.Service.ExportService exportService;

    // Yêu cầu 2: Xóa sinh viên
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);
        return "Xóa thành công sinh viên ID: " + id;
    }

    @GetMapping("/export/pdf")
    public org.springframework.http.ResponseEntity<byte[]> exportPDF() {
        try {
            List<Student> students = service.getAll();
            byte[] pdfBytes = exportService.exportToPDF(students);
            
            org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_PDF);
            headers.set(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=students.pdf");
            
            return new org.springframework.http.ResponseEntity<>(pdfBytes, headers, org.springframework.http.HttpStatus.OK);
        } catch (Exception e) {
            return new org.springframework.http.ResponseEntity<>(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/export/excel")
    public org.springframework.http.ResponseEntity<byte[]> exportExcel() {
        try {
            List<Student> students = service.getAll();
            byte[] excelBytes = exportService.exportToExcel(students);
            
            org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.set(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=students.xlsx");
            
            return new org.springframework.http.ResponseEntity<>(excelBytes, headers, org.springframework.http.HttpStatus.OK);
        } catch (Exception e) {
            return new org.springframework.http.ResponseEntity<>(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/stats")
    public java.util.Map<String, Object> getStats() {
        return service.getStats();
    }
}