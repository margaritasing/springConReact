package com.margaritasing.springwithreact.Service;


import com.margaritasing.springwithreact.Excepciones.StudentNotFoung;
import com.margaritasing.springwithreact.Model.Student;
import com.margaritasing.springwithreact.Repository.StudentRepository;
import com.margaritasing.springwithreact.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    public final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public Student saveStudent(Student student) {
        if (student.getName() != null || student.getAddress() != null)
            studentRepository.save(student);
        return student;

    }


    @Override
    public List<String> getAllStudents(Long id) {
        List<Student> studentList = studentRepository.findAll();
        return studentList
                .stream()
                .map(Student::getName)
                .collect(Collectors.toList());
    }

    @Override
    public void borrar(Long id) {
        if (studentRepository.existsById(id))
            studentRepository.deleteById(id);
        
    }

    @Override
    public List<Student> getAllDate() {
        return studentRepository.findAll();
    }

    @Override
    public StudentDto Update(Long id, StudentDto student) {
        studentRepository.findById(id).map(p -> {

            if (student.getName() != null) {
                p.setName(student.getName());
            } else student.setName(p.getName());

            if (student.getAddress() != null) {
                p.setName(student.getAddress());
            } else student.setAddress(p.getAddress());

            return studentRepository.save(p);
        }).orElseThrow(() -> new StudentNotFoung(id));
            return student;

    }
}
