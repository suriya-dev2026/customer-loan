package com.example.customerloan.customerloan.model.listeners;

import java.time.LocalDate;

import com.example.customerloan.customerloan.constants.AppConstants;
import com.example.customerloan.customerloan.model.Loan;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class LoanListener {

    @PrePersist
    public void onCreateLoan(Loan loan){
        loan.setStatus(AppConstants.ACTIVE);
        loan.setCreatedAt(LocalDate.now());
    }

    @PreUpdate
    public void onUpdateLoan(Loan loan){
        loan.setCreatedAt(LocalDate.now());
    }
}
