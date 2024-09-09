package com.khomotso.springboot_thymeleaf_crud_web_app;

import com.khomotso.springboot_thymeleaf_crud_web_app.model.Student;
import com.khomotso.springboot_thymeleaf_crud_web_app.service.StudentServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentServiceImplementation studentServiceImplementation;

    @GetMapping("/")
    public String viewHomePage(Model model){
        return findPaginated(1,"firstName","asc",model);
    }
    @GetMapping("/showNewStudentForm")
    public String showNewStudentForm(Model model){
        Student student = new Student();
        model.addAttribute("student",student);
        return "new_student";
    }
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student){
        studentServiceImplementation.addStudent(student);
        return "redirect:/";
    }
    @GetMapping("/showFormUpdate/{id}")
    public String showFormUpdate(@PathVariable("id")Long id ,Model model){
        Student student = studentServiceImplementation.getStudentById(id);
        model.addAttribute("student",student);
        return "update_student";
    }
    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable Long id){
        this.studentServiceImplementation.deleteStudentById(id);
        return "redirect:/";
    }
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable("pageNo") int pageNo, @RequestParam("sortField") String sortField,@RequestParam("sortDirection") String sortDirection, Model model){
        int pageSize = 5 ;

        Page<Student> page = studentServiceImplementation.findPaginated(pageNo,pageSize,sortField,sortDirection);
        List<Student> studentList =studentServiceImplementation.getAllStudents();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());

        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDirection",sortDirection);
        model.addAttribute("reverseSortDir",sortDirection.equals("asc") ? "desc" : "asc" );
        model.addAttribute("studentList",studentList);

        return "index";
    }
}
