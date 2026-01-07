package com.example.customerloan.customerloan.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.customerloan.customerloan.response.ValidationErrorResponse;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        Map<String,String> errorList = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
        errorList.put(error.getField(), error.getDefaultMessage()));
        ValidationErrorResponse errorResponse = new ValidationErrorResponse("error", ex.getMessage());
        errors.put("error", "validation error");
        errors.put("statuscode","422");
        errorResponse.setErrors(errors);
        errorResponse.setErrorList(errorList);
        return new ResponseEntity<ValidationErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}
