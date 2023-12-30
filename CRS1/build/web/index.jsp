<%-- 
    Document   : index
    Created on : Jul 20, 2023, 9:34:30 PM
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
    <title>Campus Recruitment System</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        body {
            background-color: #f8f9fa;
        }

        .container {
            max-width: 400px;
            margin: 0 auto;
            margin-top: 100px;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }

        .form-group {
            margin-bottom: 1rem;
        }

        .form-control:focus {
            border-color: #80bdff;
            box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
        }

        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }
    </style>
</head>

<body>
    <div class="container">
        <h3 class="text-center mb-4">Campus Recruitment System</h3>
        <form action="login" method="post">
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="role">Role</label>
                <select class="form-control" id="role" name="role" required>
                    <option value="student">Student</option>
                    <option value="recruiter">Recruiter</option>
                    <!--<option value="admin">Admin</option>-->
                </select>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-primary">Login</button>
            </div>
        </form>
            <p class="text-center mt-3">Don't have an account? 
            <br><a href="register_student.jsp">Student Registration</a>.<br>
            <!--<br><a href="register_admin.jsp">Admin Registration</a><br>-->
            <br><a href="register_recruiter.jsp">Recruiter Register</a>.
            </p>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

