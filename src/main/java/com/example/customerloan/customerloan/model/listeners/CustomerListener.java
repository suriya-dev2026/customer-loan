package com.example.customerloan.customerloan.model.listeners;

import java.time.LocalDate;
import com.example.customerloan.customerloan.constants.AppConstants;
import com.example.customerloan.customerloan.model.Customer;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class CustomerListener {

    @PrePersist
    public void onCreateCustomer(Customer customer){
        customer.setStatus(AppConstants.ACTIVE);
        customer.setCreatedAt(LocalDate.now());
    }

    @PreUpdate
    public void onUpdateCustomer(Customer customer){
        customer.setUpdatedAt(LocalDate.now());
    }
}
