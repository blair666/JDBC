package org.example;

import dao.EmployeePerformanceReviewDAO;
import dao.EmployeePerformanceReviewDAOImpl;
import model.EmployeePerformanceReview;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MainRunner {
    public static void main(String[] args) {


        String url = "jdbc:mysql://localhost:3306/classicmodels";
        String user = "teamlead";
        String password = "teamlead_password";



        try {

            Connection conn  = DriverManager.getConnection(url,user,password);
            EmployeePerformanceReviewDAO reviewDAO = new EmployeePerformanceReviewDAOImpl(conn);

            EmployeePerformanceReview newReview = new EmployeePerformanceReview(0,1002,"Excellecent employee!!!");

            reviewDAO.insertPerformanceReview(newReview);
            System.out.println("Review was added successfully");

            List<EmployeePerformanceReview> reviews = reviewDAO.getAllPerformanceReviews();

            for(EmployeePerformanceReview review : reviews) {
                System.out.println("Review ID: " + review.getReviewId() + ", Employee Number: " + review.getEmployeeNumber() +
                        ", Review: " + review.getReviewText());

            }
            conn.close();

        } catch (SQLException ex){
            ex.printStackTrace();
        }

    }
}