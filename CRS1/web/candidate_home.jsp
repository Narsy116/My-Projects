<%-- 
    Document   : candidate_home
    Created on : Jul 20, 2023, 9:34:10 PM
    Author     : Narasimha
--%>

<%@page import="com.example.model.Job"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Campus Recruitment System - Candidate Home</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        body {
            background-color: #f8f9fa;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            margin-top: 50px;
        }

        .card {
            margin-bottom: 20px;
        }

        .card-header {
            font-weight: bold;
        }

        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }
    </style>
</head>

<body>
    <div class="container">
        <h3 class="text-center mb-4">Candidate Home</h3>
        <div class="row">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">Applied Jobs</div>
                    <div class="card-body">
                        <% List<Job> appliedJobs = (List<Job>) request.getAttribute("appliedJobs");
                           if (appliedJobs != null && !appliedJobs.isEmpty()) { %>
                            <ul>
                                <% for (Job job : appliedJobs) { %>
                                    <li><%= job.getTitle() %></li>
                                <% } %>
                            </ul>
                        <% } else { %>
                            <p>No jobs applied yet.</p>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
        <div class="text-center">
            <a href="logout" class="btn btn-primary">Logout</a>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>

