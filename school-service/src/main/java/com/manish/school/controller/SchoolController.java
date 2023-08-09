package com.manish.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/school")
public class SchoolController {
    @GetMapping("/public")
    public String getPublic(){
        return "this is visible to everyone";
    }

    @GetMapping("/student")
    public String getStudent(){
        return "this is visible to student";
    }

    @GetMapping("/teacher")
    public String getTeacher(){
        return "this is visible to teacher";
    }
}
