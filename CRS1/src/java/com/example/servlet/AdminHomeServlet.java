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

import com.example.dao.AdminDAO;
import com.example.dao.AdminDAOImpl;
import com.example.dao.RecruitDAO;
import com.example.dao.RecruitDAOImpl;
import com.example.dao.StudentDAO;
import com.example.dao.StudentDAOImpl;
import com.example.model.Recruit;
import com.example.model.Recruiter;
import com.example.model.Student;


public class AdminHomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AdminDAO adminDAO;
    private RecruitDAO recruitDAO;
    private StudentDAO studentDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        adminDAO = new AdminDAOImpl();
        recruitDAO = new RecruitDAOImpl();
        studentDAO = new StudentDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Student> students = studentDAO.getAllStudents();
        List<Recruiter> recruiters = recruitDAO.getAllRecruiters();

        request.setAttribute("students", students);
        request.setAttribute("recruiters", recruiters);

        request.getRequestDispatcher("/admin_home.jsp").forward(request, response);
    }
}

