package com.panchek.wp.readmore.utils;

import com.panchek.wp.readmore.model.Book;
import info.debatty.java.stringsimilarity.experimental.Sift4;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Year;
import java.util.Comparator;
import java.util.Date;

public class BookComparator implements Comparator<Book> {
    private final String shortDescription;
    private final String language;
    private final double popularity;
    private final LocalDate datePublished;
    private final double stars;
    private final int totalPages;
    private final String authorName;
    private final String seriesName;

    public BookComparator(String shortDescription, String language, double popularity, LocalDate datePublished, double stars, int totalPages, String authorName, String seriesName) {
        this.shortDescription = shortDescription;
        this.language = language;
        this.popularity = popularity;
        this.datePublished = datePublished;
        this.stars = stars;
        this.totalPages = totalPages;
        this.authorName = authorName;
        this.seriesName = seriesName;
    }


    @Override
    public int compare(Book o1, Book o2) {
        return -Double.compare(getSimilarity(o1),getSimilarity(o2));
    }

    private double getSimilarity(Book b1){
        int bonus=0;
        if(authorName.equals(b1.getAuthor().getName()))
            bonus+=100;
        if(b1.getSeries()!=null)
            if(!seriesName.equals("") && seriesName.equals(b1.getSeries().getName()))
                bonus+=150;
        if(language.equals(b1.getLanguage()))
            bonus+=50;
        if(popularity>=b1.getPopularity())
            bonus+=20;
        if(stars>=b1.getStarRating())
            bonus+=20;
        else if(stars-b1.getStarRating()<=1.3)
            bonus+=10;
        int yearToComp=datePublished.getYear();
        int bookYear=b1.getDatePublished().getYear();
        if(Math.abs(yearToComp-bonus)<=5)
            bonus+=10;
        if(Math.abs(totalPages- b1.getPageCount())<=200)
            bonus+=10;
        Sift4 sift4=new Sift4();
        sift4.setMaxOffset(60);
        Double result=sift4.distance(shortDescription,b1.getShortDescription());
        result+=bonus;
        return result;
    }
}
