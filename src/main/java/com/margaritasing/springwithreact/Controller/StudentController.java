package com.margaritasing.springwithreact.Controller;

import com.margaritasing.springwithreact.Service.StudentService;
import com.margaritasing.springwithreact.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {

    public final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/add")
    public String add(@RequestBody StudentDto studentDto){
        studentService.saveStudent(studentDto);
        return "New student is added";
    }

    @GetMapping("/getAll")
    public List<String> list(){
        return studentService.getAllStudents();
    }


}
