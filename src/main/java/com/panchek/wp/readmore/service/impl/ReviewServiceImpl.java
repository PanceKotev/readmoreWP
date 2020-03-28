package com.panchek.wp.readmore.service.impl;

import com.panchek.wp.readmore.exception.ResourceNotFoundException;
import com.panchek.wp.readmore.model.Book;
import com.panchek.wp.readmore.model.Review;
import com.panchek.wp.readmore.model.User;
import com.panchek.wp.readmore.payload.ReviewRequest;
import com.panchek.wp.readmore.payload.ReviewResponse;
import com.panchek.wp.readmore.repository.BookRepository;
import com.panchek.wp.readmore.repository.ReviewRepository;
import com.panchek.wp.readmore.repository.UserRepository;
import com.panchek.wp.readmore.security.UserPrincipal;
import com.panchek.wp.readmore.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;
    @Override
    public List<ReviewResponse> listReviewsByUser(Long userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User id",userId));
        return reviewRepository.findAllByUser(user).stream().map(review -> mapReviewToRR(review)).collect(Collectors.toList());
    }

    @Override
    public List<ReviewResponse> listReviewsByMe(UserPrincipal currentUser) {
        User user=userRepository.findById(currentUser.getId()).orElseThrow(()->new ResourceNotFoundException("User","User id",currentUser.getId()));
        return reviewRepository.findAllByUser(user).stream().map(review -> mapReviewToRR(review)).collect(Collectors.toList());
    }

    @Override
    public List<ReviewResponse> listReviewsByBook(Long bookId) {
        Book book=bookRepository.findById(bookId).orElseThrow(()->new ResourceNotFoundException("Book","Book id",bookId));
        return reviewRepository.findAllByBook(book).stream().map(review -> mapReviewToRR(review)).collect(Collectors.toList());
    }

    @Override
    public Review createReview(UserPrincipal currentUser, ReviewRequest create) {
        User user=userRepository.findById(currentUser.getId()).orElseThrow(()->new ResourceNotFoundException("User","User id",currentUser.getId()));

        Book book=bookRepository.findById(create.getBookId()).orElseThrow(()->new ResourceNotFoundException("Book","Book id",create.getBookId()));

        return reviewRepository.save(new Review(user,book,create.getSummary(),create.getRating()));
    }

    @Override
    public boolean existsByUserIdAndBookId(Long userId, Long bookId) {
        return reviewRepository.existsByUserIdAndBookId(userId,bookId);
    }

    @Override
    public ReviewResponse getReviewById(Long reviewId) {
        Review review=reviewRepository.findById(reviewId).orElseThrow(()->new ResourceNotFoundException("Review","Review Id",reviewId));
        return new ReviewResponse(review.getId(),review.getUser().getUsername(),review.getBook().getName(),review.getSummary(),review.getRating());
    }

    private ReviewResponse mapReviewToRR(Review review){
        return new ReviewResponse(review.getId(),
                review.getUser().getUsername(),
                review.getBook().getName(),
                review.getSummary(),
                review.getRating());
    }
}
