package com.margaritasing.springwithreact.Service;

import com.margaritasing.springwithreact.Model.Student;

import java.util.List;

public interface StudentService {

    Student saveStudent(Student student);
    List<Student> getAllStudents();
    void borrar(Long id);
    Student Update(Student student);

}
