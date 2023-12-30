/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dao;

/**
 *
 * @author Narasimha
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Applicant;
import com.example.model.Job;
import com.example.model.Student;
import com.example.util.DBConnectionUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ApplicantDAOImpl implements ApplicantDAO {
    static Logger logger = LogManager.getLogger(ApplicantDAOImpl.class);
    private JobDAO jobDAO;

    public ApplicantDAOImpl() {
        jobDAO = new JobDAOImpl();
    }
    
    @Override
    public boolean applyJob(Applicant applicant) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO applicants (job_id, student_id,status,email,name) VALUES (?, ?)")) {
            preparedStatement.setInt(1, applicant.getJobId());
            preparedStatement.setInt(2, applicant.getStudentId());
            preparedStatement.setString(3, applicant.getStatus());
            preparedStatement.setString(4, applicant.getEmail());
            preparedStatement.setString(5, applicant.getName());
            
            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Applicant> getAllApplicantsForJob(int jobId) {
        List<Applicant> applicants = new ArrayList<>();
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM applicants WHERE job_id = ?")) {
            preparedStatement.setInt(1, jobId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Applicant applicant = new Applicant();
                applicant.setId(resultSet.getInt("id"));
                applicant.setJobId(resultSet.getInt("job_id"));
                applicant.setStudentId(resultSet.getInt("student_id"));
                applicant.setName(resultSet.getString("name"));
                applicant.setEmail(resultSet.getString("email"));
                applicant.setResumeUrl(resultSet.getString("resumeUrl"));
                applicants.add(applicant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applicants;
    }

    @Override
    public List<Applicant> getAllApplicationsByStudent(int studentId) {
        List<Applicant> applications = new ArrayList<>();
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM applicants WHERE student_id = ?")) {
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Applicant applicant = new Applicant();
                applicant.setId(resultSet.getInt("id"));
                applicant.setJobId(resultSet.getInt("job_id"));
                applicant.setStudentId(resultSet.getInt("student_id"));
                applications.add(applicant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applications;
    }

    @Override
    public boolean hasStudentApplied(int studentId, int jobId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT COUNT(*) FROM applicants WHERE student_id = ? AND job_id = ?")) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, jobId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getNumberOfApplicantsForJob(int jobId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT COUNT(*) FROM applicants WHERE job_id = ?")) {
            preparedStatement.setInt(1, jobId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean deleteApplicant(int applicantId) {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM applicants WHERE id = ?")) {
            preparedStatement.setInt(1, applicantId);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

 @Override
public Applicant getApplicantByJobAndStudent(int jobId, int studentId) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Applicant applicant = null;

    try {
        conn = DBConnectionUtil.getConnection();
        String sql = "SELECT * FROM applicants WHERE job_id = ? AND student_id = ?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, jobId);
        pstmt.setInt(2, studentId);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            applicant = new Applicant();
            applicant.setId(rs.getInt("id"));

            // Get the associated student
            StudentDAO studentDAO = new StudentDAOImpl();
            Student student = studentDAO.getStudentById(rs.getInt("student_id"));
            applicant.setStudent(student);

            // Get the associated job
            JobDAO jobDAO = new JobDAOImpl();
            Job job = jobDAO.getJobById(rs.getInt("job_id"));
            applicant.setJob(job);

            applicant.setStatus(rs.getString("status"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
       // DBConnectionUtil.closeConnection(conn);
    }

    return applicant;
}



    @Override
    public boolean applyForJob(Applicant applicant) {
        boolean applied = false;
        Student student = applicant.getStudent();
        Job job = applicant.getJob();
        try (Connection connection = DBConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO applicants (job_id, student_id, status,name,email) VALUES (?, ?, ?,?,?)")) {
            preparedStatement.setInt(1, job.getId());
            preparedStatement.setInt(2, student.getId());
            preparedStatement.setString(3, applicant.getStatus());
            preparedStatement.setString(4,student.getName());
            preparedStatement.setString(5, student.getEmail());
            
            //entering the data into the student_applications
            
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                insertIntoStudentApplicationsList(applicant);
                applied = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applied;
    }
    public void insertIntoStudentApplicationsList(Applicant applicant) {
        Student student = applicant.getStudent();
        Job job = applicant.getJob();
        try (Connection connection = DBConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
        ("INSERT INTO Student_Applications VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setString(4,job.getTitle());
            preparedStatement.setString(5, job.getDescription());
            preparedStatement.setString(6, job.getCompany());//need to add the company name in recruiter page while posting the job
            preparedStatement.setString(7, job.getEmail());//need to set the values later--
            preparedStatement.setString(8, "");
            
            //entering the data into the student_applications
            
            int rowsAffected = preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    @Override
public List<Applicant> getApplicantsByJobId(int id) {
    List<Applicant> applicants = new ArrayList<>();
    try (Connection connection = DBConnectionUtil.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM applicants WHERE job_id = ?")) {
        preparedStatement.setInt(1, id);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Applicant applicant = new Applicant();
                applicant.setId(resultSet.getInt("id"));
                applicant.setJobId(resultSet.getInt("job_id"));
                applicant.setStudentId(resultSet.getInt("student_id"));
                applicant.setStatus(resultSet.getString("status"));
                applicants.add(applicant);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return applicants;
}

    @Override
    public boolean registerApplicant(Applicant applicant) {
        boolean registered = false;
        try (Connection connection = DBConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO applicants (job_id, student_id, status) VALUES (?, ?, ?)")) {
            preparedStatement.setInt(1, applicant.getJobId());
            preparedStatement.setInt(2, applicant.getStudentId());
            preparedStatement.setString(3, applicant.getStatus());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                registered = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registered;
    }

//    @Override
//    public List<Applicant> getApplicantsByStudentId(int id) {
//        List<Applicant> applicants = new ArrayList<>();
//        try (Connection connection = DBConnectionUtil.getConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM applicants WHERE student_id = ?")) {
//            preparedStatement.setInt(1, id);
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                while (resultSet.next()) {
//                    Applicant applicant = new Applicant();
//                    applicant.setId(resultSet.getInt("id"));
//                    applicant.setJobId(resultSet.getInt("job_id"));
//                    applicant.setStudentId(resultSet.getInt("student_id"));
//                    applicant.setStatus(resultSet.getString("status"));
//                    applicants.add(applicant);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return applicants;
//    }
 @Override
    public List<Applicant> getApplicantsByStudentId(int studentId) {
        List<Applicant> applicants = new ArrayList<>();
        String query = "SELECT distinct job_id, a.*,b.* FROM applicants a"
                + "  join Student_Applications b on b.std_id=a.student_id WHERE a.student_id = ? ";
        logger.info(query);
        try (Connection conn = DBConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Applicant applicant = new Applicant();
                    applicant.setId(rs.getInt("id"));
                    applicant.setStatus(rs.getString("status"));
                    Job job = new Job();
                    job.setCompany(rs.getString("std_company_name"));
                    job.setDescription(rs.getString("std_job_description"));
                    job.setTitle(rs.getString("std_job_title"));
                    
                    applicant.setJob(job);
                    //int jobId = rs.getInt("job_id");
                    // Retrieve the Job object using job_id
                    //Job job = jobDAO.getJobById(jobId);
                    //applicant.setJob(job);

                    applicants.add(applicant);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return applicants;
    }

}
