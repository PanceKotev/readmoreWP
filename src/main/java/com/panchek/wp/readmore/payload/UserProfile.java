package com.panchek.wp.readmore.payload;

import com.panchek.wp.readmore.model.Book;

import java.time.Instant;
import java.util.List;

public class UserProfile {
    private Long id;
    private String username;
    private String name;
    private Instant joinedAt;
    private List<Book> likedBooks;

    public UserProfile(Long id, String username, String name, Instant joinedAt,List<Book> likedBooks) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.joinedAt = joinedAt;
        this.likedBooks = likedBooks;
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

    public List<Book> getLikedBooks() {
        return likedBooks;
    }

    public void setLikedBooks(List<Book> likedBooks) {
        this.likedBooks = likedBooks;
    }
}
