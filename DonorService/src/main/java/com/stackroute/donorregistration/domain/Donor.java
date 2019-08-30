package com.stackroute.donorregistration.domain;


import org.springframework.stereotype.Component;
import javax.persistence.Id;
import javax.persistence.Transient;


@Component
public class Donor {

    @Id
    private String id;
    private String name;
    @Transient private String email;
    @Transient private String password;
    @Transient private String role;
    private long phoneNumber;
    @Transient private int operation;

    public Donor() {
    }

    public Donor(String id, String name, String email, String password, String role, long phoneNumber,int operation) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.operation = operation;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
