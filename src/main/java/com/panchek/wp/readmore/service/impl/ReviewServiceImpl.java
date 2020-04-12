package com.panchek.wp.readmore.service.impl;

import com.panchek.wp.readmore.exception.ResourceNotFoundException;
import com.panchek.wp.readmore.exception.ReviewPermissionException;
import com.panchek.wp.readmore.model.*;
import com.panchek.wp.readmore.payload.ReviewEditRequest;
import com.panchek.wp.readmore.payload.ReviewRequest;
import com.panchek.wp.readmore.payload.ReviewResponse;
import com.panchek.wp.readmore.repository.BookRepository;
import com.panchek.wp.readmore.repository.ReviewRepository;
import com.panchek.wp.readmore.repository.RoleRepository;
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

    @Autowired
    RoleRepository roleRepository;

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
    public List<ReviewResponse> listReviewsByBook(String bookName) {
        Book book=bookRepository.findByNameEquals(bookName).orElseThrow(()->new ResourceNotFoundException("Book","Book name",bookName));
        return reviewRepository.findAllByBook(book).stream().map(review -> mapReviewToRR(review)).collect(Collectors.toList());
    }

    @Override
    public Review createReview(UserPrincipal currentUser, ReviewRequest create) {
        User user=userRepository.findById(currentUser.getId()).orElseThrow(()->new ResourceNotFoundException("User","User id",currentUser.getId()));
        Book book=bookRepository.findById(create.getBookId()).orElseThrow(()->new ResourceNotFoundException("Book","Book id",create.getBookId()));
        Review result=reviewRepository.save(new Review(user,book,create.getSummary(),create.getRating()));
        updateStarRating(book,create.getRating(),false);
        return result;
    }

    public void updateStarRating(Book b,double newRating,boolean afterDelete) {
        double division;
        if(!afterDelete)
            division=(reviewRepository.getSumOfAllRatings(b.getId())+newRating)/((reviewRepository.countReviewsByBookEquals(b)*1.0)+1);
        else
            division=reviewRepository.getSumOfAllRatings(b.getId())/(reviewRepository.countReviewsByBookEquals(b)*1.0);
        double stars=Math.round(division*2)/2;
        b.setStarRating(stars);
        b.setPopularity(BookServiceImpl.updatePopularity(b));
        bookRepository.save(b);
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

    @Override
    public void deleteReview(Long reviewId) {
        Review review=reviewRepository.findById(reviewId).orElseThrow(()->new ResourceNotFoundException("Review","Id",reviewId));
        Long bookId=review.getBook().getId();
        reviewRepository.deleteById(reviewId);
        Book book=bookRepository.findById(bookId).orElseThrow(()->new ResourceNotFoundException("Book","Book id",bookId));
        updateStarRating(book,0,true);
    }

    @Override
    public Review updateReview(UserPrincipal currentUser, ReviewEditRequest edit) {
        Review review=reviewRepository.findById(edit.getReviewId()).orElseThrow(()->new ResourceNotFoundException("Review","Review Id",edit.getReviewId()));
        Role role=roleRepository.findByName(RoleName.ROLE_ADMIN).orElseThrow(()->new ResourceNotFoundException("Role","Name",RoleName.ROLE_ADMIN));
        if(currentUser.getId()!=review.getUser().getId() && !userRepository.existsByIdEqualsAndRolesContains(currentUser.getId(),role))
            throw new ReviewPermissionException();
        if(edit.getRating()!=review.getRating()){
            updateStarRating(review.getBook(),edit.getRating(),false);
        review.setRating(edit.getRating());}
        review.setSummary(edit.getSummary());
        return reviewRepository.save(review);
    }

    public static ReviewResponse mapReviewToRR(Review review){
        return new ReviewResponse(review.getId(),
                review.getUser().getUsername(),
                review.getBook().getName(),
                review.getSummary(),
                review.getRating());
    }
}
