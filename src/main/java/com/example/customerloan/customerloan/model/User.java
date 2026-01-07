package com.example.customerloan.customerloan.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user_type")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userName;

    private String password;

    private String email;

    private String status;

    private String gender;

    @JsonIgnore
    @Column(name = "created_at")
    private LocalDate createdAt;

    @JsonIgnore
    @Column(name = "updated_at")
    private LocalDate updatedAt;
    
}
