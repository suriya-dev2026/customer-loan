package com.example.customerloan.customerloan.model;

import java.time.LocalDate;
import com.example.customerloan.customerloan.model.listeners.LoanListener;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "loans")
@Data
@EntityListeners(LoanListener.class)
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer customerId;

    private String loanType;

    private String status;

    @Column(name ="created_at")
    @JsonIgnore
    private LocalDate createdAt;

    @Column(name ="updated_at")
    @JsonIgnore
    private LocalDate updatedAt;

}
