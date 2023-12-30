/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Narasimha
 */
package com.example.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.StudentDAO;
import com.example.dao.StudentDAOImpl;
import com.example.model.Student;
import java.io.PrintWriter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class RegisterStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static Logger logger = LogManager.getLogger(RegisterStudentServlet.class);

    private StudentDAO studentDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        studentDAO = new StudentDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //PrintWriter pw = new PrintWriter(System.out);
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String mobileNumber = request.getParameter("contact");
        String universityName = request.getParameter("universityName");
        String passedOutYear = request.getParameter("passedOutYear");

        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setPassword(password);
        student.setMobileNumber(mobileNumber);
        student.setUniversity(universityName);
        student.setYearOfPassOut(passedOutYear);
        boolean isRegistered = studentDAO.registerStudent(student);
        
        logger.debug("saved successfully");
        
        if (isRegistered) {
            //pw.println("Successfully registered as a student!");
            request.setAttribute("message", "Successfully registered as a student!");
        } else {
            request.setAttribute("message", "Failed to register as a student!");
        }
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
