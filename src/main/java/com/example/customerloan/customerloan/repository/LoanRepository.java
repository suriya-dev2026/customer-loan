package com.example.customerloan.customerloan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.customerloan.customerloan.model.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Integer>{

    List<Loan> findByStatus(String status);
}
