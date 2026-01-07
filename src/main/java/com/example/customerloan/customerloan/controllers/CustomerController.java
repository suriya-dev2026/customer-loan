package com.example.customerloan.customerloan.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.example.customerloan.customerloan.message.CustomerMessage;
import com.example.customerloan.customerloan.model.Customer;
import com.example.customerloan.customerloan.request.CustomerRequest;
import com.example.customerloan.customerloan.response.CustomResponse;
import com.example.customerloan.customerloan.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(value ="customer")
@Tag(name = "Customer Controller",description = "has rest end points of customer catalog")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Operation(operationId = "getAllCustomers",
        summary = "view all Customers",
        description = "This rest end point used to getting list of customers"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode ="200",
                    description = "returns list of customers",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomerRequest.class)))
                })
    
    @GetMapping("/active")
    public ResponseEntity<CustomResponse> getAllCustomers(){
        try{
            List<Customer> customer = customerService.findAllCustomers();
            if(customer == null || customer.isEmpty()){
                CustomResponse errorResponse = new CustomResponse("error", CustomerMessage.CUSTOMER, 400);
                return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
            }
            CustomResponse errorResponse = new CustomResponse("success", CustomerMessage.LOAD_CUSTOMER, 400);
            errorResponse.setData(customer);
            return new ResponseEntity<>(errorResponse,HttpStatus.OK);
        }
        catch(Exception e){
           CustomResponse errorResponse = new CustomResponse("error",e.getMessage(), 500);
           return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    @Operation(operationId = "getCustomerById",
        summary = "get one Customer by using id",
        description = "This rest end point used to return a customer by id",
        parameters = {
                @Parameter(name ="id",
                required = true,
                description = "pass customer id")
        }
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode ="200",
                    description = "returns one customer",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomerRequest.class)))
                })
    @GetMapping("/view/{id}")
    public ResponseEntity<CustomResponse> getCustomerById(@PathVariable Integer id){
        try{
            Customer customer = customerService.findByCustomerId(id);
            if(customer == null ){
                CustomResponse errorResponse = new CustomResponse("error", CustomerMessage.CUSTOMER, 400);
                return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
            }
            CustomResponse errorResponse = new CustomResponse("success", CustomerMessage.LOAD_CUSTOMERS, 400);
            errorResponse.setRequestInfo(customer);
            return new ResponseEntity<>(errorResponse,HttpStatus.OK);
        }
        catch(Exception e){
           CustomResponse errorResponse = new CustomResponse("error",e.getMessage(), 500);
           return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    @Operation(operationId = "createCustomer",
              summary = "Adding a Customer",
              description = "This rest end point used to create customer")
    @ApiResponses(value = {
        @ApiResponse(responseCode ="201",
                    description = "Creates and returns saved customer",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomerRequest.class)))
                })
    @PostMapping("/add")
    public ResponseEntity<CustomResponse> createCustomer(@Valid @RequestBody CustomerRequest customerRequest){
        try{
            Customer customer = customerService.addCustomer(customerRequest);
            CustomResponse errorResponse = new CustomResponse("success", CustomerMessage.ADD_CUSTOMER, 200);
            errorResponse.setRequestInfo(customer);
            return new ResponseEntity<>(errorResponse,HttpStatus.OK);
        }
        catch(Exception e){
           CustomResponse errorResponse = new CustomResponse("error",e.getMessage(), 500);
           return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }
    @Operation(operationId = "updateCustomer",
        summary = "updating Customer",
        description = "This rest end point use to update customer"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode ="201",
                    description = "update and returns updated customer",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomerRequest.class)))
                })

    @PutMapping("/update")
    public ResponseEntity<CustomResponse> updateCustomer(@Valid @RequestBody CustomerRequest customerRequest){
        try{
            Customer customer = customerService.updateCustomer(customerRequest);
            CustomResponse errorResponse = new CustomResponse("success", CustomerMessage.UPDATE_CUSTOMER, 200);
            errorResponse.setRequestInfo(customer);
            return new ResponseEntity<>(errorResponse,HttpStatus.OK);
        }
        catch(Exception e){
           CustomResponse errorResponse = new CustomResponse("error",e.getMessage(), 500);
           return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    @Operation(operationId = "deleteCustomerById",
        summary = "delete customer by id",
        description = "This rest end point used to delete customer by id",
        parameters = {
                @Parameter(name ="id",
                required = true,
                description = "pass customer id")
        }
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode ="201",
                    description = "delete customer by id",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomerRequest.class)))
                })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse> deleteCustomerById(@PathVariable Integer id){
        try{
            customerService.deleteCustomerById(id);
            CustomResponse errorResponse = new CustomResponse("success", CustomerMessage.DELETE_CUSTOMER, 200);
            return new ResponseEntity<>(errorResponse,HttpStatus.OK);
        }
        catch(Exception e){
           CustomResponse errorResponse = new CustomResponse("error",e.getMessage(), 500);
           return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }
}
