package com.evoting.Model;

import java.time.LocalDate;
import java.util.Locale;

import org.hibernate.annotations.GeneratorType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name ;
    @Column
    private String email;

    @Column
    private String password;
    @Column
    private String cnfmpassword;
    @Column
    private LocalDate dateofbirth;
    @Column
    private String state;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column
    private String status;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getCnfmpassword() {
        return cnfmpassword;
    }
    public void setCnfmpassword(String cnfmpassword) {
        this.cnfmpassword = cnfmpassword;
    }
    public LocalDate getDateofbirth() {
        return dateofbirth;
    }
    public void setDateofbirth(LocalDate dateofbirth) {
        this.dateofbirth = dateofbirth;
    }


}
