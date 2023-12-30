<%-- 
    Document   : recruiter_home
    Created on : Jul 22, 2023, 11:50:44 AM
    Author     : Narasimha
--%>

<!-- recruiter_home.jsp -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Recruiter Home</title>
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="js/login.js" type="text/javascript"></script>
    <% 
        String message = (String) request.getAttribute("message")==null ? "" : (String) request.getAttribute("message");
    %>
</head>
<body onload="login.showMessage()">
    <input type="hidden" name="handleMessage" id="handleMessage" value="<%=message%>">
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <div class="container">
            <a class="navbar-brand" href="#">Campus Recruitment System</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="logout">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <h2>Welcome, ${recruiter.name}!</h2>
        <h3>Your Posted Jobs</h3>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Applied Candidates</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="job" items="${jobs}">
                    <tr>
                        <td>${job.title}</td>
                        <td>${job.description}</td>
                        <td>
                            <a href="view-applicants?jobId=${job.id}">View Applicants</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        <h3>Post New Job</h3>
        <form action="create-job" method="post">
            <div class="form-group">
                <label for="title">Title:</label>
                <input type="text" class="form-control" id="title" name="title" required>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea class="form-control" id="description" name="description" required></textarea>
            </div>
            <div class="form-group">
                <label for="salary">Salary:</label>
                <input type="number" class="form-control" id="salary" name="salary" required>
            </div>
            <button type="submit" class="btn btn-primary">Post Job</button>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

