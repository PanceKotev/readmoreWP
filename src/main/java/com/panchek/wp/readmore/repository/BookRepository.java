package com.panchek.wp.readmore.repository;

import com.panchek.wp.readmore.model.Author;
import com.panchek.wp.readmore.model.Book;
import com.panchek.wp.readmore.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findAllByViewsIsGreaterThanEqual(int popularity);

    List<Book> findAllByGenreEquals(String genre);

    List<Book> findAllBySeries(Series series);

    List<Book> findAllByAuthorEquals(Author author);

    List<Book> findAllByStarRatingGreaterThanEqual(double starRating);

    List<Book> findAllByReviewsGreaterThan(int reviewNo);

    List<Book> findAllByNameContaining(String name);

    List<Book> findAllByIdIn(List<Long> bookIds);

    Optional<Book> findByNameEquals(String bookName);

    boolean existsByName(String name);
}
