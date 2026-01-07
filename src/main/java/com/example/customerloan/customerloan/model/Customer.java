package com.example.customerloan.customerloan.model;

import java.time.LocalDate;

import com.example.customerloan.customerloan.model.listeners.CustomerListener;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name ="customers")
@Data
@EntityListeners(CustomerListener.class)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String customerName;

    private String phoneNumber;

    private String address;

    private String status;

    @JsonIgnore
    private LocalDate createdAt;

    @JsonIgnore
    private LocalDate updatedAt;

}
