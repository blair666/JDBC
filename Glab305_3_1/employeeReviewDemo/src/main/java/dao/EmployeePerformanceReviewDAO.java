package dao;
import model.EmployeePerformanceReview;

import java.util.List;

public interface EmployeePerformanceReviewDAO {
    void insertPerformanceReview(EmployeePerformanceReview review);

    List<EmployeePerformanceReview> getAllPerformanceReviews();

    void deletePerformanceReview(int reviewId);
}
