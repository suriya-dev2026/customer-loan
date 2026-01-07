package com.example.customerloan.customerloan.validators;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.customerloan.customerloan.repository.CustomerRepository;
import com.example.customerloan.customerloan.validations.ValidCustomerId;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomerIdValidator implements ConstraintValidator<ValidCustomerId, Integer>{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        return customerRepository.existsById(id);
    }
}
