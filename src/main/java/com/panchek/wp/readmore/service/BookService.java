package com.panchek.wp.readmore.service;

import com.panchek.wp.readmore.model.Author;
import com.panchek.wp.readmore.model.Book;
import com.panchek.wp.readmore.model.Series;
import com.panchek.wp.readmore.payload.BookCreation;
import com.panchek.wp.readmore.payload.BookReturn;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookReturn> listBooksByGenre(String genreName);

    List<BookReturn> listBooksBySeries(String seriesName);

    List<BookReturn> listBookByAuthor(String authorName);

    List<BookReturn> listPopularBooks();

    Book createBook(BookCreation bookCreate);

    boolean existsBook(String name);

    BookReturn findBookByName(String bookName);

    List<BookReturn> listSimilarBooksTo(Long id);


}
