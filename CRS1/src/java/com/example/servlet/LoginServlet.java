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

import com.example.dao.AdminDAO;
import com.example.dao.AdminDAOImpl;
import com.example.dao.JobDAOImpl;
import com.example.dao.RecruitDAO;
import com.example.dao.RecruitDAOImpl;
import com.example.dao.StudentDAO;
import com.example.dao.StudentDAOImpl;
import com.example.model.Admin;
import com.example.model.Job;
import com.example.model.Recruiter;
import com.example.model.Student;
import java.util.List;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private StudentDAO studentDAO;
    private RecruitDAO recruitDAO;
    private AdminDAO adminDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        studentDAO = new StudentDAOImpl();
        recruitDAO = new RecruitDAOImpl();
        adminDAO = new AdminDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        //check whether there is a email registered in our database or not
        // if registered, check what is the role assigned
        if (role != null && !role.isEmpty()) {
            if (role.equals("student")) {
                Student student = studentDAO.loginStudent(email, password);
                if (student != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("loggedInStudent", student);
                    JobDAOImpl jobDAOImpl = new JobDAOImpl();
                    List<Job> allJobs = jobDAOImpl.getAllJobs();
                    request.setAttribute("jobsAvailable", allJobs);
                    response.sendRedirect(request.getContextPath() + "/student-home");
                } else {
                    request.setAttribute("message", "Invalid email or password for student!");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
            } else if (role.equals("recruiter")) {
                Recruiter recruiter = recruitDAO.loginRecruiter(email, password);
                if (recruiter != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("loggedInRecruiter", recruiter);
                    response.sendRedirect(request.getContextPath() + "/recruiter-home");
                } else {
                    request.setAttribute("message", "Invalid email or password for recruiter!");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
            } else if (role.equals("admin")) {
                Admin admin = adminDAO.loginAdmin(email, password);
                if (admin != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("loggedInAdmin", admin);
                    response.sendRedirect(request.getContextPath() + "/admin-home");
                } else {
                    request.setAttribute("message", "Invalid email or password for admin!");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
            }
        } else {
            request.setAttribute("message", "Invalid role!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}

