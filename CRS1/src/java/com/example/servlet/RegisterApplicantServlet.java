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
import javax.servlet.http.HttpSession;

import com.example.dao.ApplicantDAO;
import com.example.dao.ApplicantDAOImpl;
import com.example.model.Applicant;
import com.example.model.Student;

public class RegisterApplicantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ApplicantDAO applicantDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        applicantDAO = new ApplicantDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("loggedInStudent");

        if (student != null) {
            int studentId = student.getId();
            int jobId = Integer.parseInt(request.getParameter("jobId"));

            Applicant applicant = new Applicant();
            applicant.setStudentId(studentId);
            applicant.setJobId(jobId);

            boolean isRegistered = applicantDAO.registerApplicant(applicant);

            if (isRegistered) {
                request.setAttribute("message", "Successfully registered as an applicant!");
            } else {
                request.setAttribute("message", "Failed to register as an applicant!");
            }
            request.getRequestDispatcher("/student_home.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
}
