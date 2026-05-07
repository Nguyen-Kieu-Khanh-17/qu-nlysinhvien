// StudentService.java
package com.example.studentmanager.Service;

import com.example.studentmanager.Entity.Student;
import com.example.studentmanager.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    public List<Student> getAll() { return repository.findAll(); }
    public Student getById(int id) { return repository.findById(id).orElse(null); }
    public Student save(Student s) { return repository.save(s); }
    public void delete(int id) { repository.deleteById(id); }
    public void deleteAll() { repository.deleteAll(); }
    public List<Student> search(String keyword) { return repository.findByNameContainingIgnoreCase(keyword); }

    public java.util.Map<String, Object> getStats() {
        List<Student> students = repository.findAll();
        long totalCount = students.size();
        double averageAge = students.stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0.0);

        java.util.Map<String, Object> stats = new java.util.HashMap<>();
        stats.put("totalCount", totalCount);
        stats.put("averageAge", Math.round(averageAge * 10.0) / 10.0);
        return stats;
    }
}