package com.khomotso.springboot_thymeleaf_crud_web_app;

import com.khomotso.springboot_thymeleaf_crud_web_app.model.Student;
import com.khomotso.springboot_thymeleaf_crud_web_app.service.StudentServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class StudentController {
    @Autowired
    private StudentServiceImplementation studentServiceImplementation;

    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("studentList",studentServiceImplementation.getAllStudents());
        return "index";
    }
    @GetMapping("/showNewStudentForm")
    public String showNewStudentForm(Model model){
        Student student = new Student();
        model.addAttribute("student",student);
        return "new_student";
    }
    @GetMapping("/saveStudent")
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
}
