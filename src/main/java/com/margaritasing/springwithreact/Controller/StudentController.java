package com.margaritasing.springwithreact.Controller;

import com.margaritasing.springwithreact.Model.Student;
import com.margaritasing.springwithreact.Service.StudentService;
import com.margaritasing.springwithreact.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {

    public final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService)  {
        this.studentService = studentService;
    }
    public MessageSource messageSource;






    @GetMapping("/getAll")
    public List<Student> list(){
        return studentService.getAllDate();
    }


    @GetMapping(value = "/name")
    public ResponseEntity<?> getName(@PathVariable Long id){
        ResponseEntity response;
        try {
            return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents(id));
        }catch (Exception e){
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageSource.getMessage("no.records.found",
                    new Object[]{"Student"}, LocaleContextHolder.getLocale()));
        }
        return response;
    }

    @PostMapping(path = "")
    public ResponseEntity<?> save (@RequestBody StudentDto studentDto) {
        try{
            Student student = new Student(studentDto.getName(), studentDto.getAddress());
            studentService.saveStudent(student);
            String successMsg = messageSource.getMessage("success.fine.student", new String[]{"Student"}, Locale.US);
            return ResponseEntity.status(HttpStatus.OK).body(successMsg);
        }catch (Exception e){
            String notFound = messageSource.getMessage("no.records.found", new String[]{"Student"}, Locale.US);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound);
        }


    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id){
        ResponseEntity response;
        try {
            studentService.borrar(id);
            response = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage("success.fine",new Object[]{"Student"},  Locale.US));
        } catch (Exception exception){
            response = ResponseEntity.status(HttpStatus.FORBIDDEN).body(messageSource.getMessage("bad.id",new Object[]{"Student"}, Locale.US));
        }
        return response;
    }


}
