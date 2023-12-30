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

import com.example.model.Recruiter;

import java.util.List;

public interface RecruiterDAO {
    List<Recruiter> getAllRecruiters();

    Recruiter getRecruiterById(int id);

    Recruiter getRecruiterByEmail(String email);

    boolean addRecruiter(Recruiter recruiter);

    boolean updateRecruiter(Recruiter recruiter);

    boolean deleteRecruiter(int id);
}

