package com.example.customerloan.customerloan.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.customerloan.customerloan.message.LoanMessage;
import com.example.customerloan.customerloan.model.Loan;
import com.example.customerloan.customerloan.request.LoanRequest;
import com.example.customerloan.customerloan.response.CustomResponse;
import com.example.customerloan.customerloan.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value ="loan")
@Tag(name = "Loan Controller",description = "has rest end points of loan catalog")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Operation(operationId = "getAllLoans",
        summary = "view all Loans",
        description = "This rest end point used to view all Loans"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode ="200",
                    description = "return list of loans",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = LoanRequest.class)))
                })
    
    @GetMapping("/active")
    public ResponseEntity<CustomResponse> getAllLoans(){
        try{
            List<Loan> loan = loanService.getAllloans();
            if(loan == null || loan.isEmpty()){
                CustomResponse errorResponse = new CustomResponse("error", LoanMessage.LOAN, 400);
                return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
            }
            CustomResponse errorResponse = new CustomResponse("error", LoanMessage.LOAD_LOANS, 200);
            errorResponse.setData(loan);
            return new ResponseEntity<>(errorResponse,HttpStatus.OK);
        }
        catch(Exception e){
           CustomResponse errorResponse = new CustomResponse("error",e.getMessage(), 500);
           return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    @Operation(operationId = "getLoanById",
        summary = "get loan by id",
        description = "This rest end point used to view loan by id",
        parameters = {
                @Parameter(name ="id",
                required = true,
                description = "pass loan id")
        }
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode ="200",
                    description = "view loan by id",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = LoanRequest.class)))
                })
    @GetMapping("/view/{id}")
    public ResponseEntity<CustomResponse> getLoanById(@PathVariable Integer id){
        try{
            Loan loan = loanService.findLoanById(id);
            if(loan == null ){
                CustomResponse errorResponse = new CustomResponse("error", LoanMessage.LOAN, 400);
                return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
            }
            CustomResponse errorResponse = new CustomResponse("error", LoanMessage.LOAD_LOAN, 200);
            errorResponse.setRequestInfo(loan);
            return new ResponseEntity<>(errorResponse,HttpStatus.OK);
        }
        catch(Exception e){
           CustomResponse errorResponse = new CustomResponse("error",e.getMessage(), 500);
           return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }
    @Operation(operationId = "addLoan",
        summary = "adding a loans",
        description = "This rest end point used to add loan"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode ="201",
                    description = "add a loan",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = LoanRequest.class)))
                })

    @PostMapping("/add")
    public ResponseEntity<CustomResponse> createLoan(@Valid @RequestBody LoanRequest loanRequest){
        try{
            Loan loan = loanService.addLoan(loanRequest);
            CustomResponse errorResponse = new CustomResponse("error", LoanMessage.ADD_LOAN, 200);
            errorResponse.setRequestInfo(loan);
            return new ResponseEntity<>(errorResponse,HttpStatus.OK);
        }
        catch(Exception e){
           CustomResponse errorResponse = new CustomResponse("error",e.getMessage(), 500);
           return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    @Operation(operationId = "updateLoan",
        summary = "updating loans",
        description = "This rest end point used to update loan"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode ="200",
                    description = "update a loan",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = LoanRequest.class)))
                })
    @PutMapping("/update")
    public ResponseEntity<CustomResponse> updateLoan(@Valid @RequestBody LoanRequest loanRequest){
        try{
            Loan loan = loanService.updateLoan(loanRequest);
            CustomResponse errorResponse = new CustomResponse("error", LoanMessage.UPDATE_LOAN, 200);
            errorResponse.setRequestInfo(loan);
            return new ResponseEntity<>(errorResponse,HttpStatus.OK);
        }
        catch(Exception e){
           CustomResponse errorResponse = new CustomResponse("error",e.getMessage(), 500);
           return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }
    @Operation(operationId = "deleteLoanById",
        summary = "delete loan by id",
        description = "This rest end point used to delete loan using id",
        parameters = {
            @Parameter(name ="id",
                required = true,
                description = "pass loan id")
        }
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode ="200",
                    description = "delete a loan",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = LoanRequest.class)))
                })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse> deleteLoanById(@PathVariable Integer id){
        try{
            loanService.deleteLoanById(id);
            CustomResponse errorResponse = new CustomResponse("error", LoanMessage.DELETE_LOAN, 200);
            return new ResponseEntity<>(errorResponse,HttpStatus.OK);
        }
        catch(Exception e){
           CustomResponse errorResponse = new CustomResponse("error",e.getMessage(), 500);
           return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }
}
