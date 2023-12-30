<%-- 
    Document   : view_applicants
    Created on : Jul 22, 2023, 3:02:32 PM
    Author     : Narasimha
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>

        <title>View Applicants</title>
        <!-- Add Bootstrap CSS link -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
         <style>
        body {
            background-color:#FFDEAD;
        }
        </style>
    </head>
    <body>
        <div class="container mt-4">
            <h2>Applicants for the Job</h2>
            <table class="table table-bordered table-hover">
                <thead class="thead-light">
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Resume</th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${not empty applicants}">
                    <c:forEach items="${applicants}" var="applicant">
                        <tr>
                            <td>${applicant.name}</td>
                            <td>${applicant.email}</td>
                            <td>
                                <a href="${applicant.resumeUrl}" class="btn btn-primary" download>Download Resume</a>
                            </td>
                        </tr>
                    </c:forEach>
                        </c:if>
                    <c:if test="${empty applicants}">
                    <p>No applicants.</p>
                </c:if>
                </tbody>
            </table>
        </div>
        <div class="justify-content-start ml-5 pl-5 d-flex">
            <!--need to implement the below-->
            <a href="recruiter-home">Go to the main page</a>
        </div>

        <!-- Add Bootstrap JS and jQuery -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
