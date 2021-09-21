package com.margaritasing.springwithreact.Service;


import com.margaritasing.springwithreact.Excepciones.StudentNotFoung;
import com.margaritasing.springwithreact.Model.Student;
import com.margaritasing.springwithreact.Repository.StudentRepository;
import com.margaritasing.springwithreact.Dto.StudentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    public final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }



    @Override
    public StudentDto saveStudent(Student student) {
        if (student.getName() != null || student.getAddress() != null)
            studentRepository.save(student);
        return convertirEntitytoStudentDto(student);

    }


    @Override
    public List<String> getAllStudents(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        return (List<String>) convertirEntitytoStudentDto(student);
    }

    @Override
    public void borrar(Long id) {
        if (studentRepository.existsById(id))
            studentRepository.deleteById(id);
        
    }

    public List<StudentDto> getAllStudentDto(){
        return studentRepository.findAll()
                .stream()
                .map(this::convertirEntitytoStudentDto)
                .collect(Collectors.toList());

    }

    @Override
    public List<Student> getAllDate() {

        return studentRepository.findAll();
    }

    @Override
    public StudentDto update(Long id, StudentDto studentDto) {
        studentRepository.findById(id).map(p -> {

            if (studentDto.getName() != null) {
                p.setName(studentDto.getName());
            } else studentDto.setName(p.getName());

            if (studentDto.getAddress() != null) {
                p.setName(studentDto.getAddress());
            } else studentDto.setAddress(p.getAddress());

            return studentRepository.save(p);
        }).orElseThrow(() -> new StudentNotFoung(id));
            return studentDto;

    }

    private StudentDto convertirEntitytoStudentDto(Student student){
        return modelMapper.map(student, StudentDto.class);

    }

    private Student convertirDtoToEntity(Student studentDto){
        return modelMapper.map(studentDto, Student.class);
    }




}
