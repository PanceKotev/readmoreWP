package com.panchek.wp.readmore.payload;

import com.panchek.wp.readmore.model.Genre;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class BookReturn {
    private Long id;

    private String name;

    private String authorName;

    private List<String> genreNames;

    private String cover;

    private String language;

    private int nmbrReviews;

    private int nmbrLikes;

    private String downloadList;

    private String shortDescription;

    private String seriesName;

    private LocalDate datePublished;

    private double starRating;

    private double popularity;

    private boolean liked;

    public BookReturn(Long id, String name, String authorName, List<String> genreNames, String cover, String language, int nmbrReviews, int nmbrLikes, String downloadList, String shortDescription, String seriesName, LocalDate datePublished, double starRating, double popularity,boolean liked) {
        this.id = id;
        this.name = name;
        this.authorName = authorName;
        this.genreNames = genreNames;
        this.cover = cover;
        this.language = language;
        this.nmbrReviews = nmbrReviews;
        this.nmbrLikes = nmbrLikes;
        this.downloadList = downloadList;
        this.shortDescription = shortDescription;
        this.seriesName = seriesName;
        this.datePublished = datePublished;
        this.starRating = starRating;
        this.popularity = popularity;
        this.liked=liked;
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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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

    public int getNmbrReviews() {
        return nmbrReviews;
    }

    public void setNmbrReviews(int nmbrReviews) {
        this.nmbrReviews = nmbrReviews;
    }

    public int getNmbrLikes() {
        return nmbrLikes;
    }

    public void setNmbrLikes(int nmbrLikes) {
        this.nmbrLikes = nmbrLikes;
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

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public LocalDate getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(LocalDate datePublished) {
        this.datePublished = datePublished;
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


    public List<String> getGenreNames() {
        return genreNames;
    }

    public void setGenreNames(List<String> genreNames) {
        this.genreNames = genreNames;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
