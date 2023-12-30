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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Job;
import com.example.util.DBConnectionUtil;

public class JobDAOImpl implements JobDAO {

    @Override
    public boolean createJob(Job job) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO jobs (title, description, recruiter_id,salary) VALUES (?, ?, ?,?)")) {
            preparedStatement.setString(1, job.getTitle());
            preparedStatement.setString(2, job.getDescription());
            preparedStatement.setInt(3, job.getRecruiterId());
            preparedStatement.setDouble(4, job.getSalary());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateJob(Job job) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE jobs SET title = ?, description = ? WHERE id = ?")) {
            preparedStatement.setString(1, job.getTitle());
            preparedStatement.setString(2, job.getDescription());
            preparedStatement.setInt(3, job.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteJob(int jobId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM jobs WHERE id = ?")) {
            preparedStatement.setInt(1, jobId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Job> getAllJobs() {
        List<Job> jobs = new ArrayList<>();
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM jobs");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Job job = new Job();
                job.setId(resultSet.getInt("id"));
                job.setTitle(resultSet.getString("title"));
                job.setDescription(resultSet.getString("description"));
                job.setRecruiterId(resultSet.getInt("recruiter_id"));
                jobs.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }

    @Override
    public Job getJobById(int jobId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM jobs WHERE id = ?")) {
            preparedStatement.setInt(1, jobId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Job job = new Job();
                job.setId(resultSet.getInt("id"));
                job.setTitle(resultSet.getString("title"));
                job.setDescription(resultSet.getString("description"));
                job.setRecruiterId(resultSet.getInt("recruiter_id"));
                job.setSalary(resultSet.getDouble("salary"));
                return job;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Job> getJobsByRecruiter(int recruiterId) {
        List<Job> jobs = new ArrayList<>();
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM jobs WHERE recruiter_id = ?")) {
            preparedStatement.setInt(1, recruiterId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Job job = new Job();
                job.setId(resultSet.getInt("id"));
                job.setTitle(resultSet.getString("title"));
                job.setDescription(resultSet.getString("description"));
                job.setRecruiterId(resultSet.getInt("recruiter_id"));
                jobs.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }

    @Override
    public List<Job> getJobsByRecruiterId(int id) {
        List<Job> jobs = new ArrayList<>();
        try (Connection connection = DBConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM jobs WHERE recruiter_id = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Job job = new Job();
                    job.setId(resultSet.getInt("id"));
                    job.setTitle(resultSet.getString("title"));
                    job.setDescription(resultSet.getString("description"));
                    job.setRecruiterId(resultSet.getInt("recruiter_id"));
                    jobs.add(job);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }

    public List<Job> getAllJobsAppliedByStudent(int id) {
        List<Job> jobs = new ArrayList<>();
        try (Connection connection = DBConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement
        ("SELECT * FROM student_applications WHERE student_id = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Job job = new Job();
                    job.setTitle(resultSet.getString("std_job_title"));
                    job.setDescription(resultSet.getString("std_job_description"));
                    job.setCompany(resultSet.getString("std_company_name"));
                    jobs.add(job);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }

//    @Override
//    public List<Job> getAllJobsAppliedByStudent(int id) {
//        List<Job> jobs = new ArrayList<>();
//        try (Connection connection = DBConnectionUtil.getConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement
//        ("SELECT * FROM student_applications WHERE student_rid = ?")) {
//            preparedStatement.setInt(1, id);
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                while (resultSet.next()) {
//                    Job job = new Job();
//                    job.setId(resultSet.getInt("id"));
//                    job.setTitle(resultSet.getString("title"));
//                    job.setDescription(resultSet.getString("description"));
//                    job.setRecruiterId(resultSet.getInt("recruiter_id"));
//                    jobs.add(job);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return jobs;
//    }
}
