package com.example.servlet;

import java.io.IOException;

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

public class ApplyJobServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ApplicantDAO applicantDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        applicantDAO = new ApplicantDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("loggedInStudent");
        String jobIdString = request.getParameter("jobId");

        if (student != null && jobIdString != null) {
            int jobId = Integer.parseInt(jobIdString);
            Job job = new Job();
            job.setId(jobId);
            JobDAO jobDAO = new JobDAOImpl();
            Job jobDetails = jobDAO.getJobById(jobId);

            // Check if the student has already applied for this job
            Applicant existingApplicant = applicantDAO.getApplicantByJobAndStudent(jobId, student.getId());

            if (existingApplicant != null) {
                response.getWriter().println("You have already applied for this job.");
                return;
            }

            Applicant applicant = new Applicant();
            applicant.setStudent(student);
            applicant.setJob(jobDetails);
            applicant.setStatus("Applied");
            applicant.setJobId(jobDetails.getId());

            boolean success = applicantDAO.applyForJob(applicant);

            if (success) {
//                response.sendRedirect(request.getContextPath() + "/student_home");
                  request.setAttribute("message", "Successfully applied for the Job!");
//                  request.getRequestDispatcher("/student_home.jsp").forward(request, response);
                  request.getRequestDispatcher("/student-home").forward(request, response);
            } else {
                // Handle error, e.g., show an error message on the page
                response.getWriter().println("Failed to apply for the job.");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
}
