package com.panchek.wp.readmore.controller;

import com.panchek.wp.readmore.model.Book;
import com.panchek.wp.readmore.model.Review;
import com.panchek.wp.readmore.payload.ApiResponse;
import com.panchek.wp.readmore.payload.ReviewRequest;
import com.panchek.wp.readmore.payload.ReviewResponse;
import com.panchek.wp.readmore.security.CurrentUser;
import com.panchek.wp.readmore.security.UserPrincipal;
import com.panchek.wp.readmore.service.impl.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    ReviewServiceImpl reviewService;

    @GetMapping("/user/{userId}")
    public List<ReviewResponse> findReviewsByUser(@PathVariable(value = "userId")Long userId){
        return reviewService.listReviewsByUser(userId);
    }
    @GetMapping("/me")
    public List<ReviewResponse> findReviewsByUser(@CurrentUser UserPrincipal currentUser){
        return reviewService.listReviewsByMe(currentUser);
    }
    @GetMapping("/book/{bookId}")
    public List<ReviewResponse> findReviewsByBook(@PathVariable(value="bookId") Long bookId){
        return reviewService.listReviewsByBook(bookId);
    }
    @PostMapping("/create")
    public ResponseEntity<?> createReview(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody ReviewRequest reviewRequest){
        if(reviewService.existsByUserIdAndBookId(currentUser.getId(),reviewRequest.getBookId())){
            return new ResponseEntity(new ApiResponse(false,"You already left a review on this book!"),
                    HttpStatus.BAD_REQUEST);
        }
        Review result=reviewService.createReview(currentUser,reviewRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/review/{reviewId}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "Review created successfully!"));
    }
    @GetMapping("/{reviewId}")
    public ReviewResponse returnReview(@PathVariable(value = "reviewId")Long reviewId){
        return reviewService.getReviewById(reviewId);
    }
}
