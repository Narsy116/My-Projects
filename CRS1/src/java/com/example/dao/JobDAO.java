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

import com.example.model.Job;

public interface JobDAO {
    // Method to create a new job posting
    boolean createJob(Job job);

    // Method to update an existing job posting
    boolean updateJob(Job job);

    // Method to delete a job posting
    boolean deleteJob(int jobId);

    // Method to get all job postings
    List<Job> getAllJobs();

    // Method to get a specific job posting by ID
    public Job getJobById(int jobId);

    // Method to get all job postings created by a specific recruiter
    List<Job> getJobsByRecruiter(int recruiterId);

    public List<Job> getJobsByRecruiterId(int id);
    
    //Method to get the list of jobs applied by a specific student
    List<Job> getAllJobsAppliedByStudent(int id);
}
