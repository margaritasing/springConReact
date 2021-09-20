package com.margaritasing.springwithreact.Excepciones;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotFoung extends RuntimeException {

    public StudentNotFoung(Long id) {
        super("No se ha encontrado usuario con el id: " + id);    }
}
