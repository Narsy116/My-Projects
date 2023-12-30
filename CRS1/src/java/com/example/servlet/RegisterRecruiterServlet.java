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

import com.example.dao.RecruitDAO;
import com.example.dao.RecruitDAOImpl;
import com.example.model.Recruiter;

public class RegisterRecruiterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private RecruitDAO recruitDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        recruitDAO = new RecruitDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Recruiter recruiter = new Recruiter();
        recruiter.setName(name);
        recruiter.setEmail(email);
        recruiter.setPassword(password);

        boolean isRegistered = recruitDAO.registerRecruiter(recruiter);

        if (isRegistered) {
            request.setAttribute("message", "Successfully registered as a recruiter!");
        } else {
            request.setAttribute("message", "Failed to register as a recruiter!");
        }
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}

