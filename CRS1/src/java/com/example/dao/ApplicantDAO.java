/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

/**
 *
 * @author Narasimha
 */

import java.util.List;
import com.example.model.Applicant;

public interface ApplicantDAO {
    // Method to save a job application
    boolean applyJob(Applicant applicant);

    // Method to get all job applications for a specific job
    List<Applicant> getAllApplicantsForJob(int jobId);

    // Method to get all job applications submitted by a specific student
    List<Applicant> getAllApplicationsByStudent(int studentId);

    // Method to check if a student has already applied for a job
    boolean hasStudentApplied(int studentId, int jobId);

    // Method to get the number of applicants for a specific job
    int getNumberOfApplicantsForJob(int jobId);

    // Method to delete a job application
    boolean deleteApplicant(int applicantId);

    public Applicant getApplicantByJobAndStudent(int jobId, int id);

    public boolean applyForJob(Applicant applicant);

    public List<Applicant> getApplicantsByJobId(int id);

    public boolean registerApplicant(Applicant applicant);

    public List<Applicant> getApplicantsByStudentId(int id);
}

