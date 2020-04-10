package com.panchek.wp.readmore.payload;

import com.panchek.wp.readmore.model.Book;

import java.time.Instant;
import java.util.List;

public class UserProfile {
    private Long id;
    private String username;
    private String name;
    private Instant joinedAt;
    private List<BookReturn> likedBooks;
    private List<ReviewResponse> reviews;

    public UserProfile(Long id, String username, String name, Instant joinedAt,List<BookReturn> likedBooks,List<ReviewResponse> reviews) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.joinedAt = joinedAt;
        this.likedBooks = likedBooks;
        this.reviews=reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Instant joinedAt) {
        this.joinedAt = joinedAt;
    }

    public List<BookReturn> getLikedBooks() {
        return likedBooks;
    }

    public void setLikedBooks(List<BookReturn> likedBooks) {
        this.likedBooks = likedBooks;
    }

    public List<ReviewResponse> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewResponse> reviews) {
        this.reviews = reviews;
    }
}
