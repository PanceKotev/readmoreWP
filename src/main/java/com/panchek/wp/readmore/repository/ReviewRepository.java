package com.panchek.wp.readmore.repository;

import com.panchek.wp.readmore.model.Book;
import com.panchek.wp.readmore.model.Review;
import com.panchek.wp.readmore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    int countReviewsByBookEquals(Book b);

    @Query("select sum(r.rating) from Review r where r.book.id = :bookId ")
    double getSumOfAllRatings(Long bookId);

    List<Review> findAllByBook(Book b);

    List<Review> findAllByUser(User user);
    @Query("select case when count(r)> 0 then true else false end from Review r where r.user.id = :userId and r.book.id = :bookId ")
    boolean existsByUserIdAndBookId(Long userId,Long bookId);

    void deleteById(Long reviewId);

    void deleteAllByIdIn(List<Long> reviewIds);
}
