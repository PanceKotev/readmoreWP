package com.panchek.wp.readmore.repository;

import com.panchek.wp.readmore.model.Book;
import com.panchek.wp.readmore.model.Role;
import com.panchek.wp.readmore.model.RoleName;
import com.panchek.wp.readmore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username,String email);
    List<User> findAllByIdIn(List<Long> userIds);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    int countUsersByLikedBooksContaining(Book b);
    boolean existsByIdEqualsAndRolesContains(Long id, Role role);
}
