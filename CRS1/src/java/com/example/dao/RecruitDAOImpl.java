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

import com.example.model.Recruiter;
import com.example.util.DBConnectionUtil;

public class RecruitDAOImpl implements RecruitDAO {

    @Override
    public boolean registerRecruiter(Recruiter recruiter) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO recruiters (name, email, password) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, recruiter.getName());
            preparedStatement.setString(2, recruiter.getEmail());
            preparedStatement.setString(3, recruiter.getPassword());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateRecruiter(Recruiter recruiter) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE recruiters SET name = ?, email = ?, password = ? WHERE id = ?")) {
            preparedStatement.setString(1, recruiter.getName());
            preparedStatement.setString(2, recruiter.getEmail());
            preparedStatement.setString(3, recruiter.getPassword());
            preparedStatement.setInt(4, recruiter.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteRecruiter(int recruiterId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM recruiters WHERE id = ?")) {
            preparedStatement.setInt(1, recruiterId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Recruiter> getAllRecruiters() {
        List<Recruiter> recruiters = new ArrayList<>();
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM recruiters");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Recruiter recruiter = new Recruiter();
                recruiter.setId(resultSet.getInt("id"));
                recruiter.setName(resultSet.getString("name"));
                recruiter.setEmail(resultSet.getString("email"));
                recruiter.setPassword(resultSet.getString("password"));
                recruiters.add(recruiter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recruiters;
    }

    @Override
    public Recruiter getRecruiterById(int recruiterId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM recruiters WHERE id = ?")) {
            preparedStatement.setInt(1, recruiterId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Recruiter recruiter = new Recruiter();
                recruiter.setId(resultSet.getInt("id"));
                recruiter.setName(resultSet.getString("name"));
                recruiter.setEmail(resultSet.getString("email"));
                recruiter.setPassword(resultSet.getString("password"));
                return recruiter;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Recruiter loginRecruiter(String email, String password) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM recruiters WHERE email = ? AND password = ?")) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Recruiter recruiter = new Recruiter();
                recruiter.setId(resultSet.getInt("id"));
                recruiter.setName(resultSet.getString("name"));
                recruiter.setEmail(resultSet.getString("email"));
                recruiter.setPassword(resultSet.getString("password"));
                return recruiter;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

