// StudentRepository.java
package com.example.studentmanager.Repository;

import com.example.studentmanager.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    // Thêm hàm tìm kiếm theo tên (Yêu cầu 3)
    List<Student> findByNameContainingIgnoreCase(String name);
}