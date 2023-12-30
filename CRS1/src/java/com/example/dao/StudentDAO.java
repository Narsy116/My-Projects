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

import com.example.model.Student;

public interface StudentDAO {
    // Method to register a new student
    boolean registerStudent(Student student);

    // Method to update an existing student's information
    boolean updateStudent(Student student);

    // Method to delete a student
    boolean deleteStudent(int studentId);

    // Method to get all students
    List<Student> getAllStudents();

    // Method to get a specific student by ID
    Student getStudentById(int studentId);

    // Method to get a student by email and password for login authentication
    Student loginStudent(String email, String password);
}
