package com.example.customerloan.customerloan.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CustomResponse {

    Map<String, String> headers = new HashMap<>();

    private List<?> data;

    private Object requestInfo;

    private String message;

    public CustomResponse(String status, String message, Integer statusCode){
        this.headers.put("status",status);
        this.headers.put("message",message);
        this.headers.put("statusCode", statusCode.toString());
    }

}
