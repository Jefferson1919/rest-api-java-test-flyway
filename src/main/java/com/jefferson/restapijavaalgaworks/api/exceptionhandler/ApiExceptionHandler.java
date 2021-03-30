package com.jefferson.restapijavaalgaworks.api.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        var campos = new ArrayList<Problem.Field>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String message = error.getDefaultMessage();
            String name = ((FieldError) error).getField();
            campos.add(new Problem.Field(name, message));
        }
        var problem = new Problem();
        problem.setStatus(status.value());
        problem.setTitle("Um ou mais campos estão inválidos. Faça o preenchimento correto " +
                "e tente novamente.");
        problem.setDateTime(LocalDateTime.now());

        problem.setFields(campos);
        return super.handleExceptionInternal(ex, problem, headers, status, request);
    }
}
