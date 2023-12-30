<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Home</title>
    <!-- Add Bootstrap CSS link -->
    <link href="css/styles.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
  
<script src="js/studentHome.js" type="text/javascript"></script>
<%
    String message = (String) request.getAttribute("message") == null ? "" : (String) request.getAttribute("message");
%>
<body onload="student.showMessage()">

<style>
    body {
    background-color: #F5DEB3;
</style>      
    <input type="hidden" name="handleMessage" id="handleMessage" value="<%=message%>">
    <div class="container mt-4">
        <h1>Welcome, ${loggedInStudent.name}!</h1>
        <div class="row justify-content-end"> <a href="logout">Logout</a></div>
        <div class="row mt-4">
            <!-- Display the applied jobs -->
            <div class="col-md-6">
                <h2>Applied Jobs</h2>
                <c:if test="${not empty appliedJobs}">
                    <ul class="list-group">
                        <c:forEach items="${appliedJobs}" var="applicant">
                            <li class="list-group-item">${applicant.job.title} - ${applicant.job.company} (Status: ${applicant.status})</li>
                            <!-- Display other details of the applied job -->
                        </c:forEach>
                    </ul>
                </c:if>
                <c:if test="${empty appliedJobs}">
                    <p>No applied jobs yet.</p>
                </c:if>
  
            </div>

            <!-- Display the available jobs -->
            <div class="col-md-6">
                <h2>Available Jobs</h2>
                <c:if test="${not empty availableJobs}">
                    <ul class="list-group">
                        <c:forEach items="${availableJobs}" var="job">
                            <li class="list-group-item">
                                ${job.title} - ${job.company}
                                <div class="d-flex justify-content-end"><a href="apply-job?jobId=${job.id}" class="btn-primary btn-sm ml-2">Apply</a></div>
                            </li>
                            <!-- Display other details of the available job -->
                        </c:forEach>
                    </ul>
                </c:if>
                <c:if test="${empty availableJobs}">
                    <p>No available jobs.</p>
                </c:if>
            </div>
        </div>
    </div>

    <!-- Add Bootstrap JS and jQuery links -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
