package com.panchek.wp.readmore.service.impl;

import com.panchek.wp.readmore.exception.ResourceNotFoundException;
import com.panchek.wp.readmore.model.Author;
import com.panchek.wp.readmore.model.Book;
import com.panchek.wp.readmore.model.Series;
import com.panchek.wp.readmore.payload.BookCreation;
import com.panchek.wp.readmore.payload.BookReturn;
import com.panchek.wp.readmore.repository.AuthorRepository;
import com.panchek.wp.readmore.repository.BookRepository;
import com.panchek.wp.readmore.repository.SeriesRepository;
import com.panchek.wp.readmore.service.BookService;
import com.panchek.wp.readmore.utils.BookComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    SeriesRepository seriesRepository;
    @Override
    public List<BookReturn> listBooksByGenre(String genre) {
        return bookRepository.findAllByGenreEquals(genre).stream().map(book -> mapBookToBR(book))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookReturn> listBooksBySeries(String seriesName) {
        Series series=seriesRepository.findByNameEquals(seriesName).orElseThrow(()->new ResourceNotFoundException("Series","Series Name",seriesName));
        return bookRepository.findAllBySeries(series).stream().map(book -> mapBookToBR(book)
        ).collect(Collectors.toList());
    }

    @Override
    public List<BookReturn> listBookByAuthor(String authorName) {
        Author author=authorRepository.findByName(authorName).orElseThrow(()->new ResourceNotFoundException("Author","Author Name",authorName));
        return bookRepository.findAllByAuthorEquals(author).stream().map(book ->mapBookToBR(book))
                .collect(Collectors.toList());
    }


    @Override
    public List<BookReturn> listPopularBooks() {
        return null;
    }

    @Override
    public Book createBook(BookCreation bookCreate) {
        Author author=authorRepository.findByName(bookCreate.getAuthor()).orElse(authorRepository.save(new Author(bookCreate.getAuthor())));
        Book newBook=new Book(bookCreate.getName(),
                author,bookCreate.getGenre(),bookCreate.getCover(),
                bookCreate.getLanguage(),bookCreate.getDownloadList(),
                bookCreate.getShortDescription(),bookCreate.getDatePublished(),
                bookCreate.getPageCount());
        return bookRepository.save(newBook);
    }

    @Override
    public boolean existsBook(String name) {
        return bookRepository.existsByName(name);
    }

    @Override
    public BookReturn findBookByName(String bookName) {
        Book b=bookRepository.findByNameEquals(bookName).orElseThrow(()->new ResourceNotFoundException("Book","Title",bookName));
        b.setViews(b.getViews()+1);
        bookRepository.save(b);
        return mapBookToBR(b);
    }

    @Override
    public List<BookReturn> listSimilarBooksTo(Long id) {
        Book b=bookRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Book","Id",id));
        String seriesName="";
        if(b.getSeries()!=null)
            seriesName=b.getSeries().getName();
        List<Book> booksSameGenre=bookRepository.findAllByGenreEquals(b.getGenre());
        booksSameGenre.remove(b.getId());
        Collections.sort(booksSameGenre,new BookComparator(b.getShortDescription(),b.getLanguage(),
                b.getPopularity(),
                b.getDatePublished(),
                b.getStarRating(),
                b.getPageCount(),
                b.getAuthor().getName(),
                seriesName
        ));
        int lastIndex=5;
        if(booksSameGenre.size()<=5)
            lastIndex=booksSameGenre.size()-1;
        booksSameGenre.subList(0,lastIndex);
        return booksSameGenre.stream().map(book -> mapBookToBR(book)).collect(Collectors.toList());
    }

    public BookReturn mapBookToBR(Book book){
        int reviewSize=0;
        int likedBy=0;
        String seriesName="";
        if(book.getReviews()!=null)
            reviewSize=book.getReviews().size();
        if(book.getLikedBy()!=null)
            likedBy=book.getLikedBy().size();
        if(book.getSeries()!=null)
            seriesName=book.getSeries().getName();
        return new BookReturn(
                book.getId(),
                book.getName(),
                book.getAuthor().getName(),
                book.getGenre(),
                book.getCover(),
                book.getLanguage(),
                reviewSize,
                likedBy,
                book.getDownloadList(),
                book.getShortDescription(),
                seriesName,
                book.getDatePublished(),
                book.getStarRating(),
                book.getPopularity());
    }
}
