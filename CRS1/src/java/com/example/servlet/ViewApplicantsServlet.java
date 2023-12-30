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

import com.example.dao.ApplicantDAO;
import com.example.dao.ApplicantDAOImpl;
import com.example.model.Applicant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class ViewApplicantsServlet extends HttpServlet {

    private ApplicantDAO applicantDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        applicantDAO = new ApplicantDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the jobId from the query parameter
        int jobId = Integer.parseInt(request.getParameter("jobId"));

        // Fetch the list of applicants for the specified jobId
        List<Applicant> applicants = applicantDAO.getApplicantsByJobId(jobId);

        // Set the applicants list as a request attribute to display in the JSP
        request.setAttribute("applicants", applicants);

        // Forward the request to the view_applicants.jsp
        request.getRequestDispatcher("/WEB-INF/views/view_applicants.jsp").forward(request, response);
    }
}

