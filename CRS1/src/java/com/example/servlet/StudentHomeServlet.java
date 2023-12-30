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
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dao.ApplicantDAO;
import com.example.dao.ApplicantDAOImpl;
import com.example.dao.JobDAO;
import com.example.dao.JobDAOImpl;
import com.example.model.Applicant;
import com.example.model.Job;
import com.example.model.Student;

public class StudentHomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private JobDAO jobDAO;
    private ApplicantDAO applicantDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        jobDAO = new JobDAOImpl();
        applicantDAO = new ApplicantDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("loggedInStudent");

        if (student != null) {
            List<Job> jobs = jobDAO.getAllJobs();
            List<Applicant> applicants = applicantDAO.getApplicantsByStudentId(student.getId());
            
            request.setAttribute("availableJobs", jobs);
            request.setAttribute("appliedJobs", applicants);

            request.getRequestDispatcher("/student_home.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
}
