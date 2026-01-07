package com.example.customerloan.customerloan.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.customerloan.customerloan.constants.AppConstants;
import com.example.customerloan.customerloan.exceptions.RecordNotfoundException;
import com.example.customerloan.customerloan.model.Loan;
import com.example.customerloan.customerloan.repository.LoanRepository;
import com.example.customerloan.customerloan.request.LoanRequest;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public List<Loan> getAllloans(){
        return loanRepository.findByStatus("active");
    }

    public Loan findLoanById(Integer id){
        return loanRepository.findById(id).orElseThrow(()-> new RecordNotfoundException("loan id " + id + " is not found" ));
    }

    public Loan addLoan(LoanRequest loanRequest){
        Loan loan = new Loan();
        loan.setCustomerId(loanRequest.getCustomerId());
        loan.setLoanType(loanRequest.getLoanType());
        return loanRepository.save(loan);
    }

    public Loan updateLoan(LoanRequest loanRequest){
        Loan loan = findLoanById(loanRequest.getId());
        loan.setCustomerId(loanRequest.getCustomerId());
        loan.setLoanType(loanRequest.getLoanType());
        return loanRepository.save(loan);
    }

    public void deleteLoanById(Integer id){
        Loan loan = findLoanById(id);
        loan.setStatus(AppConstants.INACTIVE);
        loanRepository.save(loan);
    }
}
