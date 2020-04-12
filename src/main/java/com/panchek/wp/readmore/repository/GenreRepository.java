package com.panchek.wp.readmore.repository;

import com.panchek.wp.readmore.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {

    Optional<Genre> findByNameEquals(String genreName);
}
