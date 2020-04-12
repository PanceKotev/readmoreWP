package com.panchek.wp.readmore.service.impl;

import com.panchek.wp.readmore.exception.BadRequestException;
import com.panchek.wp.readmore.exception.ResourceNotFoundException;
import com.panchek.wp.readmore.model.Book;
import com.panchek.wp.readmore.model.User;
import com.panchek.wp.readmore.payload.BookReturn;
import com.panchek.wp.readmore.repository.BookRepository;
import com.panchek.wp.readmore.repository.UserRepository;
import com.panchek.wp.readmore.security.UserPrincipal;
import com.panchek.wp.readmore.service.BookService;
import com.panchek.wp.readmore.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;

    @Override
    public BookReturn likeBook(UserPrincipal currentUser, Long bookId) {
        if(!bookRepository.bookLikedBy(currentUser.getId(),bookId)){
        User user=userRepository.findById(currentUser.getId()).orElseThrow(()->new ResourceNotFoundException("User","User id",currentUser.getId()));
        Book book=bookRepository.findById(bookId).orElseThrow(()->new ResourceNotFoundException("Book","Book id",bookId));
        List<Book> likedBooks=user.getLikedBooks();
        likedBooks.add(book);
        user.setLikedBooks(likedBooks);
        userRepository.save(user);
        BookServiceImpl.updatePopularity(book);
        return BookServiceImpl.mapBookToBR(book,true);}
        else {
                throw new BadRequestException("You've already liked this book!");
        }

    }

    @Override
    public BookReturn unlikeBook(UserPrincipal currentUser, Long bookId) {
        if (bookRepository.bookLikedBy(currentUser.getId(),bookId)) {
            User user = userRepository.findById(currentUser.getId()).orElseThrow(() -> new ResourceNotFoundException("User", "User id", currentUser.getId()));
            Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book", "Book id", bookId));
            List<Book> likedBooks = user.getLikedBooks();
            likedBooks.remove(book);
            user.setLikedBooks(likedBooks);
            userRepository.save(user);
            BookServiceImpl.updatePopularity(book);
            return BookServiceImpl.mapBookToBR(book, false);
        }
        else{
            throw new BadRequestException("You haven't liked this book");
        }
    }


}
