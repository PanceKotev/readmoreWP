package com.panchek.wp.readmore.payload;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ReviewEditRequest {
    @NotNull
    private long reviewId;

    @Size(max=150)
    private String summary;

    @Range(max=5)
    private double rating;

    public ReviewEditRequest(@NotNull long reviewId, @Size(max = 150) String summary, @Range(max = 5) double rating) {
        this.reviewId = reviewId;
        this.summary = summary;
        this.rating = rating;
    }



    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public long getReviewId() {
        return reviewId;
    }

    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
    }
}
