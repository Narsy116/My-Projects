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

import com.example.dao.JobDAO;
import com.example.dao.JobDAOImpl;
import com.example.model.Job;
import com.example.model.Recruiter;

public class CreateJobServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private JobDAO jobDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        jobDAO = new JobDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String salary = request.getParameter("salary");
        double sal = Double.parseDouble(salary);  
        // Get the logged-in recruiter from the session
        HttpSession session = request.getSession();
        Recruiter recruiter = (Recruiter) session.getAttribute("loggedInRecruiter");

        if (recruiter != null) {
            Job job = new Job();
            job.setTitle(title);
            job.setDescription(description);
            job.setRecruiterId(recruiter.getId());
            job.setSalary(sal);

            boolean isJobCreated = jobDAO.createJob(job);

            if (isJobCreated) {
                request.setAttribute("message", "Job created successfully!");
            } else {
                request.setAttribute("message", "Failed to create the job!");
            }
            request.getRequestDispatcher("/WEB-INF/views/recruiter_home.jsp").forward(request, response);
            //request.getRequestDispatcher("/recruiter-home").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
}

