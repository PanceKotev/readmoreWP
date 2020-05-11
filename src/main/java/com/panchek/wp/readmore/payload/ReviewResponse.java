package com.panchek.wp.readmore.payload;

public class ReviewResponse {
    private Long id;

    private String userName;

    private String bookName;

    private String summary;

    private double rating;

    private boolean reviewedBy;

    public ReviewResponse(Long id, String userName, String bookName, String summary, double rating, boolean reviewedBy) {
        this.id = id;
        this.userName = userName;
        this.bookName = bookName;
        this.summary = summary;
        this.rating = rating;
        this.reviewedBy = reviewedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
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

    public boolean isReviewedBy() {
        return reviewedBy;
    }

    public void setReviewedBy(boolean reviewedBy) {
        this.reviewedBy = reviewedBy;
    }
}
