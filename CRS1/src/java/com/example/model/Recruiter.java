/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Narasimha
 */
package com.example.model;

public class Recruiter {
    private int id;
    private String name;
    private String email;
    private String password;
    private String companyName;

    public Recruiter() {
    }

    public Recruiter(int id, String name, String email, String password, String companyName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.companyName = companyName;
    }

    public Recruiter(int id, String name, String email, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Getters and Setters for the Recruiter attributes
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
