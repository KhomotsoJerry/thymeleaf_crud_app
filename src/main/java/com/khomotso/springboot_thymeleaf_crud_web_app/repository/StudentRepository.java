package com.khomotso.springboot_thymeleaf_crud_web_app.repository;

import com.khomotso.springboot_thymeleaf_crud_web_app.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
