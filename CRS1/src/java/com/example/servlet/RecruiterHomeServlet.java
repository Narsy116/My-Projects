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

import com.example.dao.JobDAO;
import com.example.dao.JobDAOImpl;
import com.example.dao.RecruiterDAO;
import com.example.dao.RecruiterDAOImpl;
import com.example.model.Job;
import com.example.model.Recruiter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RecruiterHomeServlet extends HttpServlet {

    private JobDAO jobDAO;
    private RecruiterDAO recruiterDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        jobDAO = new JobDAOImpl();
        recruiterDAO = new RecruiterDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the current logged-in recruiter
        Recruiter recruiter = (Recruiter) request.getSession().getAttribute("loggedInRecruiter");

        // Fetch the list of jobs posted by this recruiter
        List<Job> jobs = jobDAO.getJobsByRecruiterId(recruiter.getId());

        // Set the jobs list as a request attribute to display in the JSP
        request.setAttribute("jobs", jobs);

        // Forward the request to the recruiter_home.jsp
        request.getRequestDispatcher("/WEB-INF/views/recruiter_home.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handling the form submission for posting a new job
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        // Get the current logged-in recruiter
        Recruiter recruiter = (Recruiter) request.getSession().getAttribute("loggedInUser");

        // Create a new job
        Job job = new Job();
        job.setTitle(title);
        job.setDescription(description);
        job.setRecruiterId(recruiter.getId());

        // Save the job to the database
        jobDAO.createJob(job);

        // Redirect the recruiter back to their home page after job creation
        response.sendRedirect(request.getContextPath() + "/recruiter-home");
    }
}
