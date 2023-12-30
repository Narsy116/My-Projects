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

import com.example.model.Recruiter;
import com.example.util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecruiterDAOImpl implements RecruiterDAO {

    @Override
    public List<Recruiter> getAllRecruiters() {
        List<Recruiter> recruiters = new ArrayList<>();
        String query = "SELECT * FROM recruiters";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");

                Recruiter recruiter = new Recruiter(id, name, email, password);
                recruiters.add(recruiter);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recruiters;
    }

    @Override
    public Recruiter getRecruiterById(int id) {
        Recruiter recruiter = null;
        String query = "SELECT * FROM recruiters WHERE id=?";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");

                    recruiter = new Recruiter(id, name, email, password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recruiter;
    }

    @Override
    public Recruiter getRecruiterByEmail(String email) {
        Recruiter recruiter = null;
        String query = "SELECT * FROM recruiters WHERE email=?";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String password = resultSet.getString("password");

                    recruiter = new Recruiter(id, name, email, password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recruiter;
    }

    @Override
    public boolean addRecruiter(Recruiter recruiter) {
        String query = "INSERT INTO recruiters (name, email, password) VALUES (?, ?, ?)";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, recruiter.getName());
            preparedStatement.setString(2, recruiter.getEmail());
            preparedStatement.setString(3, recruiter.getPassword());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateRecruiter(Recruiter recruiter) {
        String query = "UPDATE recruiters SET name=?, email=?, password=? WHERE id=?";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, recruiter.getName());
            preparedStatement.setString(2, recruiter.getEmail());
            preparedStatement.setString(3, recruiter.getPassword());
            preparedStatement.setInt(4, recruiter.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteRecruiter(int id) {
        String query = "DELETE FROM recruiters WHERE id=?";
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
