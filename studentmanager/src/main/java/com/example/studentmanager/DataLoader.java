package com.example.studentmanager;

import com.example.studentmanager.Entity.Student;
import com.example.studentmanager.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        // Clear corrupted data
        studentRepository.deleteAll();

        Student s1 = new Student();
        s1.setName("Nguy\u1EC5n V\u0103n A");
        s1.setAge(20);
        s1.setEmail("nguyenvana@example.com");
        s1.setAvatar("https://i.pravatar.cc/150?u=1");

        Student s2 = new Student();
        s2.setName("Tr\u1EA7n Th\u1ECB B");
        s2.setAge(21);
        s2.setEmail("tranthib@example.com");
        s2.setAvatar("https://i.pravatar.cc/150?u=2");

        Student s3 = new Student();
        s3.setName("L\u00EA V\u0103n C");
        s3.setAge(22);
        s3.setEmail("levanc@example.com");
        s3.setAvatar("https://i.pravatar.cc/150?u=3");

        studentRepository.save(s1);
        studentRepository.save(s2);
        studentRepository.save(s3);
        System.out.println("Đã xóa dữ liệu cũ và thêm lại dữ liệu mẫu thành công!");
    }
}
