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

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public boolean existsByEmailAndIdNot(String email, int id) {
        return repository.existsByEmailAndIdNot(email, id);
    }

    public java.util.Map<String, Long> getAdvancedStats() {
        List<Student> students = repository.findAll();
        long under18 = students.stream().filter(s -> s.getAge() < 18).count();
        long from18To22 = students.stream().filter(s -> s.getAge() >= 18 && s.getAge() <= 22).count();
        long from23To30 = students.stream().filter(s -> s.getAge() >= 23 && s.getAge() <= 30).count();
        long over30 = students.stream().filter(s -> s.getAge() > 30).count();

        java.util.Map<String, Long> advStats = new java.util.HashMap<>();
        advStats.put("under18", under18);
        advStats.put("from18To22", from18To22);
        advStats.put("from23To30", from23To30);
        advStats.put("over30", over30);
        return advStats;
    }
}