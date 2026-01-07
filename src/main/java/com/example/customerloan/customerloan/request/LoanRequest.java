package com.example.customerloan.customerloan.request;

import com.example.customerloan.customerloan.validations.ValidCustomerId;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema
public class LoanRequest {

    private Integer id;

    @Schema(name ="customer id", description = "enter integer value")
    @ValidCustomerId(message = "customer id does not exists")
    @NotNull(message = "customer id cannot be null")
    private Integer customerId;

    @Schema(name ="loan type", description = "enter string value")
    @NotNull(message = "loan type cannot be null")
    @NotBlank(message = "loan type cannot be blank")
    private String loanType;

}
