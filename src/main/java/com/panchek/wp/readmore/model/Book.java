package com.panchek.wp.readmore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name="author_id", nullable = false)
    private Author author;

    @NotNull
    private String genre;

    @NotNull
    private String cover;

    private String language;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="liked_books",
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name="user_id"))
    private List<User> likedBy;

    @OneToMany(mappedBy = "book")
    private List<Review> reviews;

    private String downloadList;

    @JsonIgnore
    private int views;

    @NotNull
    private String shortDescription;

    @ManyToOne
    @JoinColumn(name = "series_id")
    private Series series;

    @NotNull
    private LocalDate datePublished;

    @NotNull
    private int pageCount;


    private double starRating;


    private double popularity;


    public Book() {
    }

    public Book(String name, Author author,  String genre,  String cover, String language,String downloadList, String shortDescription,  LocalDate datePublished,  int pageCount) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.cover = cover;
        this.language = language;
        this.downloadList=downloadList;
        this.shortDescription = shortDescription;
        this.datePublished = datePublished;
        this.pageCount = pageCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getDownloadList() {
        return downloadList;
    }

    public void setDownloadList(String downloadList) {
        this.downloadList = downloadList;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public LocalDate getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(LocalDate datePublished) {
        this.datePublished = datePublished;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public double getStarRating() {
        return starRating;
    }

    public void setStarRating(double starRating) {
        this.starRating = starRating;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public List<User> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(List<User> likedBy) {
        this.likedBy = likedBy;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
