package com.panchek.wp.readmore.service;

import com.panchek.wp.readmore.model.Author;
import com.panchek.wp.readmore.model.Book;
import com.panchek.wp.readmore.model.Series;
import com.panchek.wp.readmore.model.User;
import com.panchek.wp.readmore.payload.BookCreation;
import com.panchek.wp.readmore.payload.BookReturn;
import com.panchek.wp.readmore.security.UserPrincipal;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookReturn> listBooksByGenre(UserPrincipal currentUser,String genreName);

    List<BookReturn> listBooksBySeries(UserPrincipal currentUser,String seriesName);

    List<BookReturn> listBookByAuthor(UserPrincipal currentUser,String authorName);

    List<BookReturn> listPopularBooks(UserPrincipal currentUser);

    Book createBook(BookCreation bookCreate);

    boolean existsBook(String name);

    BookReturn findBookByName(UserPrincipal currentUser,String bookName);

    List<BookReturn> listSimilarBooksTo(Long id);

}
