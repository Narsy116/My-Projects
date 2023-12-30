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

import java.util.List;

public interface AdminDAO {
    Admin getAdminByEmail(String email);

    List<Admin> getAllAdmins();

    void addAdmin(Admin admin);

    void updateAdmin(Admin admin);

    void deleteAdmin(int adminId);

    // Add other methods related to Admin data access if needed

    public Admin loginAdmin(String email, String password);
}

