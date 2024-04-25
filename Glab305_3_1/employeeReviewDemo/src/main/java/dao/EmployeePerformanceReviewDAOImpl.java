package dao;
import model.EmployeePerformanceReview;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeePerformanceReviewDAOImpl implements EmployeePerformanceReviewDAO{
    private Connection conn;

    public EmployeePerformanceReviewDAOImpl(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void insertPerformanceReview(EmployeePerformanceReview review) {

        String sql = "INSERT INTO EmployeePerformanceReviews (reviewId, employeeNumber, reviewText) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, review.getReviewId());
            pstmt.setInt(2, review.getEmployeeNumber());
            pstmt.setString(3, review.getReviewText());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public List<EmployeePerformanceReview> getAllPerformanceReviews() {
        List<EmployeePerformanceReview> reviews = new ArrayList<>();
        String sql = "SELECT * FROM EmployeePerformanceReviews";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                reviews.add(new EmployeePerformanceReview(
                        rs.getInt("reviewId"),
                        rs.getInt("employeeNumber"),
                        rs.getString("reviewText")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }



    @Override
    public void deletePerformanceReview(int reviewId) {
        String sql = "DELETE FROM EmployeePerformanceReviews WHERE reviewId = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reviewId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}

