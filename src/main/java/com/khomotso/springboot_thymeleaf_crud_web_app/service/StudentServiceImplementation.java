package com.khomotso.springboot_thymeleaf_crud_web_app.service;

import com.khomotso.springboot_thymeleaf_crud_web_app.model.Student;
import com.khomotso.springboot_thymeleaf_crud_web_app.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImplementation implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {

        Optional<Student> optional = studentRepository.findById(id);
        Student student = null;
        if (optional.isPresent()){
            student = optional.get();
        }else{
            throw new RuntimeException("student with an id::"+id+" is not found");
        }
        return student;
    }

    @Override
    public void deleteStudentById(Long id) {
        this.studentRepository.deleteById(id);
    }

    @Override
    public Page<Student> findPaginated(int pageNo, int pageSize,String sortField,String sortDirection) {
        Sort sort = sortField.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).descending() : Sort.by(sortField).ascending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        return this.studentRepository.findAll(pageable);
    }
}
