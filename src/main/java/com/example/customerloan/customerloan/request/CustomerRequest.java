package com.example.customerloan.customerloan.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "Customer Request class")
public class CustomerRequest {

    private Integer id ;
    
    @Schema(name ="customerName", description = "enter a string value")
    @NotNull(message = "customer name cannot be null")
    @NotBlank(message = "customer name cannot be blank")
    private String customerName;

    @Schema(name ="phoneNumber", description = "enter a string value")
    @NotNull(message = "customer phone number cannot be null")
    @NotBlank(message = "customer phone number cannot be blank")
    @Pattern(regexp = "^\\d{10}$", message = "phone number must be 10 digits")
    private String phoneNumber;

    @Schema(name ="address", description = "enter a string value")
    @NotNull(message = "customer address cannot be null")
    @NotBlank(message = "customer address cannot be blank")
    private String address;

}
