package com.panchek.wp.readmore.repository;

import com.panchek.wp.readmore.model.Author;
import com.panchek.wp.readmore.model.Book;
import com.panchek.wp.readmore.model.Genre;
import com.panchek.wp.readmore.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findAllByViewsIsGreaterThanEqual(int popularity);

    List<Book> findAllByGenresContains(Genre genre);

    List<Book> findAllBySeries(Series series);

    List<Book> findAllByAuthorEquals(Author author);

    List<Book> findAllByStarRatingGreaterThanEqual(double starRating);

    List<Book> findAllByReviewsGreaterThan(int reviewNo);

    List<Book> findAllByNameContaining(String name);

    int countBooksBySeriesEquals(Series s);

    List<Book> findAllByIdIn(List<Long> bookIds);

    List<Book> findTop5ByOrderByPopularityDesc();

    Optional<Book> findByNameEquals(String bookName);

    boolean existsByName(String name);

    @Query("select avg(b.popularity) from Book b")
    double getAveragePopularity();

    @Query("select case when count(distinct b)> 0 then true else false end from Book b join b.likedBy user where user.id=:userId and b.id=:bookId ")
    boolean bookLikedBy(Long userId,Long bookId);

}
