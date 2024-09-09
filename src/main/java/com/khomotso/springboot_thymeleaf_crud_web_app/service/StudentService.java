package com.khomotso.springboot_thymeleaf_crud_web_app.service;

import com.khomotso.springboot_thymeleaf_crud_web_app.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    List<Student> getAllStudents();
    void addStudent(Student student);
    Student getStudentById(Long id);
}
