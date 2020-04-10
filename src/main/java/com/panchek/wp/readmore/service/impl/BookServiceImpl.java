package com.panchek.wp.readmore.service.impl;

import com.panchek.wp.readmore.exception.ResourceNotFoundException;
import com.panchek.wp.readmore.model.Author;
import com.panchek.wp.readmore.model.Book;
import com.panchek.wp.readmore.model.Genre;
import com.panchek.wp.readmore.model.Series;
import com.panchek.wp.readmore.payload.BookCreation;
import com.panchek.wp.readmore.payload.BookReturn;
import com.panchek.wp.readmore.repository.*;
import com.panchek.wp.readmore.service.BookService;
import com.panchek.wp.readmore.utils.BookComparator;
import com.panchek.wp.readmore.utils.PopularityComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public List<BookReturn> listBooksByGenre(String genreName) {
        Genre genre=genreRepository.findByName(genreName).orElseThrow(()->new ResourceNotFoundException("Genre","Genre name",genreName));
        List<BookReturn> result=bookRepository.findAllByGenresContains(genre).stream().map(book -> mapBookToBR(book)).collect(Collectors.toList());
        Collections.sort(result,new PopularityComparator());
        return result;
    }

    @Override
    public List<BookReturn> listBooksBySeries(String seriesName) {
        Series series=seriesRepository.findByName(seriesName).orElseThrow(()->new ResourceNotFoundException("Series","Series Name",seriesName));
        List<BookReturn> result=bookRepository.findAllBySeries(series).stream().map(book -> mapBookToBR(book)
        ).collect(Collectors.toList());
        Collections.sort(result,new PopularityComparator());
        return result;
    }

    @Override
    public List<BookReturn> listBookByAuthor(String authorName) {
        Author author=authorRepository.findByName(authorName).orElseThrow(()->new ResourceNotFoundException("Author","Author Name",authorName));
       List<BookReturn> result= bookRepository.findAllByAuthorEquals(author).stream().map(book ->mapBookToBR(book)).collect(Collectors.toList());
       Collections.sort(result,new PopularityComparator());
       return result;
    }


    @Override
    public List<BookReturn> listPopularBooks() {
        return bookRepository.findTop5ByOrderByPopularityDesc().stream().map(book->mapBookToBR(book)).collect(Collectors.toList());
    }

    @Override
    public Book createBook(BookCreation bookCreate) {
        Author author=authorRepository.findByName(bookCreate.getAuthor().trim().toLowerCase()).orElse(authorRepository.save(new Author(bookCreate.getAuthor().trim().toLowerCase())));
        List<Genre> genres=bookCreate.getGenreNames().stream().map(genreName->{
            return genreRepository.findByName(genreName.trim().toLowerCase()).orElse(genreRepository.save(new Genre(genreName.trim().toLowerCase())));
        }).collect(Collectors.toList());
        Series series=seriesRepository.findByName(bookCreate.getSeriesName().trim().toLowerCase()).orElse(seriesRepository.save(new Series(bookCreate.getSeriesName().trim().toLowerCase(),false,author)));
        Book newBook=new Book(bookCreate.getName().trim().toLowerCase(),
                author,genres,bookCreate.getCover(),
                bookCreate.getLanguage().trim().toLowerCase(),bookCreate.getDownloadList(),
                bookCreate.getShortDescription(),bookCreate.getDatePublished(),
                bookCreate.getPageCount(),
                series);
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
        b.setPopularity(updatePopularity(b));
        bookRepository.save(b);
        return mapBookToBR(b);
    }

    @Override
    public List<BookReturn> listSimilarBooksTo(Long id) {
        Book b=bookRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Book","Id",id));
        String seriesName="";
        if(b.getSeries()!=null)
            seriesName=b.getSeries().getName();
        List<Book> booksSameGenre=bookRepository.findAll();
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

    public static double updatePopularity(Book b) {
        int likedBy=b.getLikedBy().size();
        int nmrReviews=b.getReviews().size();
        int viewCount=b.getViews();
        double starCount=b.getStarRating();
        return (0.5*likedBy)+(0.3*nmrReviews)+(0.05*viewCount)+starCount;
    }



    public static BookReturn mapBookToBR(Book book){
        int reviewSize=0;
        int likedBy=0;
        String seriesName="";
        if(book.getReviews()!=null)
            reviewSize=book.getReviews().size();
        if(book.getLikedBy()!=null)
            likedBy=book.getLikedBy().size();
        if(book.getSeries()!=null)
            seriesName=book.getSeries().getName();
        List<String> genres=book.getGenres().stream().map(genre->genre.getName()).collect(Collectors.toList());
        return new BookReturn(
                book.getId(),
                book.getName(),
                book.getAuthor().getName(),
                genres,
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
