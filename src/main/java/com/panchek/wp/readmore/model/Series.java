package com.panchek.wp.readmore.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="series")
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;


    private boolean completed;

    @ManyToOne
    private Author Author;

    @OneToMany(mappedBy = "series")
    private List<Book> books;

    public Series() {
    }

    public Series(@NotNull String name, boolean completed, com.panchek.wp.readmore.model.Author author, List<Book> books) {
        this.name = name;
        this.completed = completed;
        Author = author;
        this.books = books;
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public com.panchek.wp.readmore.model.Author getAuthor() {
        return Author;
    }

    public void setAuthor(com.panchek.wp.readmore.model.Author author) {
        Author = author;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
