package com.margaritasing.springwithreact.Service;

import com.margaritasing.springwithreact.Model.Student;
import com.margaritasing.springwithreact.Dto.StudentDto;

import java.util.List;

public interface StudentService {

    StudentDto saveStudent(Student student);

    List<String> getAllStudents(Long id);

    void borrar(Long id);

    List<Student> getAllDate();

    StudentDto update(Long id, StudentDto student);

}
