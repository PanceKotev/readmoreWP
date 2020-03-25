package com.panchek.wp.readmore.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "author")
    private List<Book> booksWritten;


    public Author(){

    }

    public Author(String name){
        this.name=name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooksWritten() {
        return booksWritten;
    }

    public void setBooksWritten(List<Book> booksWritten) {
        this.booksWritten = booksWritten;
    }
}
