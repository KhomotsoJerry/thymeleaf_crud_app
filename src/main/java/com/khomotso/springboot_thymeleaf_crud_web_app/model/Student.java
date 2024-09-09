package com.khomotso.springboot_thymeleaf_crud_web_app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
