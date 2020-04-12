package com.panchek.wp.readmore.service;

import com.panchek.wp.readmore.model.Review;
import com.panchek.wp.readmore.payload.ReviewEditRequest;
import com.panchek.wp.readmore.payload.ReviewRequest;
import com.panchek.wp.readmore.payload.ReviewResponse;
import com.panchek.wp.readmore.security.UserPrincipal;

import java.util.List;

public interface ReviewService {

    List<ReviewResponse> listReviewsByUser(Long userId);

    List<ReviewResponse> listReviewsByMe(UserPrincipal currentUser);

    List<ReviewResponse> listReviewsByBook(String bookName);

    Review createReview(UserPrincipal currentUser,ReviewRequest create);

    boolean existsByUserIdAndBookId(Long userId, Long bookId);

    ReviewResponse getReviewById(Long reviewId);

    void deleteReview(Long reviewId);

    Review updateReview(UserPrincipal currentUser, ReviewEditRequest edit);
}
