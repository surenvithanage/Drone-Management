package com.server.musalasoft.drone_management.handler;

import com.server.musalasoft.drone_management.bean.errorresponse.ErrorResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
@RestController
@ResponseBody
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponseBean error = new ErrorResponseBean("Invalid Arguments", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(ConstraintViolationException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponseBean error = new ErrorResponseBean("Server Error", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponseBean error = new ErrorResponseBean("Server Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleRecordNotFoundException(RecordNotFoundException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponseBean error = new ErrorResponseBean("Record Not Found", details);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponseBean error = new ErrorResponseBean("Record Not Found", details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
