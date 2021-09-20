package com.margaritasing.springwithreact.Service;

import com.margaritasing.springwithreact.Model.Student;
import com.margaritasing.springwithreact.dto.StudentDto;

import java.util.List;

public interface StudentService {

    Student saveStudent(StudentDto student);

    List<String> getAllStudents();

    void borrar(Long id);

    StudentDto Update(Long id, StudentDto student);

}
