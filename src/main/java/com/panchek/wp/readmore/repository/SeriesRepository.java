package com.panchek.wp.readmore.repository;

import com.panchek.wp.readmore.model.Book;
import com.panchek.wp.readmore.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeriesRepository extends JpaRepository<Series,Long> {

    Optional<Series> findByBooksContaining(Book book);

    Optional<Series> findByNameEquals(String name);

}
