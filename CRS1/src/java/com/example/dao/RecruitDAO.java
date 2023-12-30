/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Narasimha
 */

package com.example.dao;

import java.util.List;

import com.example.model.Recruiter;

public interface RecruitDAO {
    // Method to register a new recruiter
    boolean registerRecruiter(Recruiter recruiter);

    // Method to update an existing recruiter's information
    boolean updateRecruiter(Recruiter recruiter);

    // Method to delete a recruiter
    boolean deleteRecruiter(int recruiterId);

    // Method to get all recruiters
    List<Recruiter> getAllRecruiters();

    // Method to get a specific recruiter by ID
    Recruiter getRecruiterById(int recruiterId);

    // Method to get a recruiter by email and password for login authentication
    Recruiter loginRecruiter(String email, String password);
}