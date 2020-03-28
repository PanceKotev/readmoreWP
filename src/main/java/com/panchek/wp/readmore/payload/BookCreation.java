package com.panchek.wp.readmore.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class BookCreation {

    @NotBlank
    @Size(min=3,max=40)
    String name;
    @NotBlank
    @Size(min=10, max=150)
    String shortDescription;

    List<String> genreNames;
    @NotBlank
    String cover;
    @NotBlank
    String author;

    LocalDate datePublished;

    int pageCount;
    @NotBlank
    String language;
    @NotBlank
    String downloadList;

    String seriesName;


    public BookCreation(String name, String shortDescription, List<String> genreNames, String cover, String author, LocalDate datePublished, int pageCount, String language, String downloadList,String seriesName) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.genreNames = genreNames;
        this.cover = cover;
        this.author = author;
        this.datePublished = datePublished;
        this.pageCount = pageCount;
        this.language = language;
        this.downloadList = downloadList;
        this.seriesName = seriesName;
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

    public List<String> getGenreNames() {
        return genreNames;
    }

    public void setGenreNames(List<String> genreNames) {
        this.genreNames = genreNames;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }
}
