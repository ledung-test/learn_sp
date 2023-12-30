package com.example.thymeleaf.controller;

import com.example.thymeleaf.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {
    List<Student> studentList = List.of(
            new Student(1,"Nguyễn Văn A", "testA@gmail.com", "012345678", 9),
        new Student(2,"Nguyễn Văn B", "testB@gmail.com", "012345678", 6),
        new Student(3,"Nguyễn Văn C", "testC@gmail.com", "012345678", 3),
        new Student(4,"Nguyễn Văn D", "testD@gmail.com", "012345678", 6),
        new Student(5,"Nguyễn Văn E", "testE@gmail.com", "012345678", 5)
    );


    @GetMapping("/")
    public String getPage(Model model, @RequestParam(required = false) String rank){
        List<Student> students = new ArrayList<>();
        if (rank != null){
            if (rank.equals("gioi")){
                students = studentList.stream()
                        .filter(student -> student.getScore() > 8)
                        .toList();
            }else if (rank.equals("kha")){
                students = studentList.stream()
                        .filter(student -> student.getScore() <= 8)
                        .toList();
            }
        }else {
            students = studentList;
        }
        System.out.println(students);
        model.addAttribute("studentList", students);
        return "index";
    }

    @GetMapping("/student/{id}")
    public String getStudentDetail(@PathVariable int id, Model model){
        Student student = (Student) studentList.stream().filter(s -> s.getId() == id ).findFirst().orElse(null);
        model.addAttribute("student", student);
        return "student-detail";
    }
    @GetMapping("/blog")
    public String getBlogPage(){
        return "admin/blog";
    }
}
