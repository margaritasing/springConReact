package com.margaritasing.springwithreact.Service;

import com.margaritasing.springwithreact.Model.Student;

import java.util.List;

public interface StudentService {

    public Student saveStudent(Student student);
    public List<Student> getAllStudents();
}
