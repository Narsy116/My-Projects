<%-- 
    Document   : admin_home
    Created on : Jul 20, 2023, 9:34:00 PM
    Author     : Narasimha
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Campus Recruitment System - Admin Home</title>
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
        <h3 class="text-center mb-4">Admin Home</h3>
        <div class="row">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">Registered Students</div>
                    <div class="card-body">
                        <p>Total number of registered students: <%= request.getAttribute("totalStudents") %></p>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">Registered Recruiters</div>
                    <div class="card-body">
                        <p>Total number of registered recruiters: <%= request.getAttribute("totalRecruiters") %></p>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">Jobs Posted</div>
                    <div class="card-body">
                        <p>Total number of posted jobs: <%= request.getAttribute("totalJobs") %></p>
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
