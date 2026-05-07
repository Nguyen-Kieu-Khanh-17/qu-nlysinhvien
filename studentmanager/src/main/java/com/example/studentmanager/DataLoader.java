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
        s1.setName("Nguyễn Nhật Nam");
        s1.setAge(20);
        s1.setEmail("nhatnam@gmail.com");
        s1.setAvatar("https://i.pravatar.cc/150?u=nam");

        Student s2 = new Student();
        s2.setName("Lê Thu Hà");
        s2.setAge(21);
        s2.setEmail("thuha@gmail.com");
        s2.setAvatar("https://i.pravatar.cc/150?u=ha");

        Student s3 = new Student();
        s3.setName("Trần Minh Quân");
        s3.setAge(22);
        s3.setEmail("minhquan@gmail.com");
        s3.setAvatar("https://i.pravatar.cc/150?u=quan");

        studentRepository.save(s1);
        studentRepository.save(s2);
        studentRepository.save(s3);
        System.out.println("Đã xóa dữ liệu cũ và thêm lại dữ liệu mẫu thành công!");
    }
}
