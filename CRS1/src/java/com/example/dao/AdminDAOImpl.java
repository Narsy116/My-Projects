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

import com.example.model.Admin;
import com.example.util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {

    @Override
    public Admin getAdminByEmail(String email) {
        Admin admin = null;
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM admins WHERE email = ?")) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    admin = new Admin();
                    admin.setId(resultSet.getInt("id"));
                    admin.setName(resultSet.getString("name"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setPassword(resultSet.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public List<Admin> getAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM admins");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setName(resultSet.getString("name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                admins.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }

    @Override
    public void addAdmin(Admin admin) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO admins (name, email, password) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, admin.getName());
            preparedStatement.setString(2, admin.getEmail());
            preparedStatement.setString(3, admin.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAdmin(Admin admin) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE admins SET name = ?, email = ?, password = ? WHERE id = ?")) {
            preparedStatement.setString(1, admin.getName());
            preparedStatement.setString(2, admin.getEmail());
            preparedStatement.setString(3, admin.getPassword());
            preparedStatement.setInt(4, admin.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAdmin(int adminId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM admins WHERE id = ?")) {
            preparedStatement.setInt(1, adminId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add other method implementations related to Admin data access if needed

    @Override
    public Admin loginAdmin(String email, String password) {
        Admin admin = null;
        try (Connection connection = DBConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM admins WHERE email = ? AND password = ?")) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    admin = new Admin();
                    admin.setId(resultSet.getInt("id"));
                    admin.setName(resultSet.getString("name"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setPassword(resultSet.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

}
