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
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.example.model.Student;
import com.example.util.DBConnectionUtil;

public class StudentDAOImpl implements StudentDAO {
    static Logger logger = LogManager.getLogger(StudentDAOImpl.class);
    @Override
    public boolean registerStudent(Student student) {
        try (
                Connection connection = DBConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO students (name, email, password, phone, university, graduation_year) VALUES (?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getPassword());
            preparedStatement.setString(4, student.getMobileNumber());
            preparedStatement.setString(5, student.getUniversity());
            preparedStatement.setString(6, student.getYearOfPassOut());
            
            logger.debug("Executed Query : " + preparedStatement);
            
            int rowsAffected = preparedStatement.executeUpdate();
            
            logger.debug("Executed Query : " + preparedStatement);
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateStudent(Student student) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE students SET name = ?, email = ?, password = ? WHERE id = ?")) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getPassword());
            preparedStatement.setInt(4, student.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteStudent(int studentId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM students WHERE id = ?")) {
            preparedStatement.setInt(1, studentId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM students");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setEmail(resultSet.getString("email"));
                student.setPassword(resultSet.getString("password"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public Student getStudentById(int studentId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM students WHERE id = ?")) {
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setEmail(resultSet.getString("email"));
                student.setPassword(resultSet.getString("password"));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Student loginStudent(String email, String password) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM students WHERE email = ? AND password = ?")) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setEmail(resultSet.getString("email"));
                student.setPassword(resultSet.getString("password"));
                student.setMobileNumber(resultSet.getString("phone"));
                student.setUniversity(resultSet.getString("university"));
                student.setYearOfPassOut(resultSet.getString("graduation_year"));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
