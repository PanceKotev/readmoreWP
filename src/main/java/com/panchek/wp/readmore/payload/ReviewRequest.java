package com.panchek.wp.readmore.payload;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ReviewRequest {
    @NotNull
    private long bookId;

    @Size(max=150)
    private String summary;

    @Range(max=5)
    private double rating;

    public ReviewRequest(@NotNull long bookId, @Size(max = 150) String summary, @Range(max = 5) double rating) {
        this.bookId = bookId;
        this.summary = summary;
        this.rating = rating;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
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
}
