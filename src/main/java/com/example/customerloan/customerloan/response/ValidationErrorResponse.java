package com.example.customerloan.customerloan.response;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class ValidationErrorResponse {
    
    Map<String,String> errors = new HashMap<>();

    Map<String,String> errorList = new HashMap<>();

    public ValidationErrorResponse(String status, String message){
        this.errors = errors;
        this.errorList = errorList;
    }
}
