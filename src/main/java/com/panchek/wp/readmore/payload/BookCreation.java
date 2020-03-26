package com.panchek.wp.readmore.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class BookCreation {

    @NotBlank
    @Size(min=3,max=40)
    String name;
    @NotBlank
    @Size(min=10, max=150)
    String shortDescription;
    @NotBlank
    String genre;
    @NotBlank
    String cover;
    @NotBlank
    String author;

    Date datePublished;

    int pageCount;
    @NotBlank
    String language;
    @NotBlank
    String downloadList;


    public BookCreation(String name, String shortDescription, String genre, String cover, String author, Date datePublished, int pageCount, String language, String downloadList) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.genre = genre;
        this.cover = cover;
        this.author = author;
        this.datePublished = datePublished;
        this.pageCount = pageCount;
        this.language = language;
        this.downloadList = downloadList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDownloadList() {
        return downloadList;
    }

    public void setDownloadList(String downloadList) {
        this.downloadList = downloadList;
    }
}
