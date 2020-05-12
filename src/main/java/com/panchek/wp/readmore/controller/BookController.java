package com.panchek.wp.readmore.controller;

import com.panchek.wp.readmore.model.Book;
import com.panchek.wp.readmore.model.User;
import com.panchek.wp.readmore.payload.ApiResponse;
import com.panchek.wp.readmore.payload.BookCreation;
import com.panchek.wp.readmore.payload.BookReturn;
import com.panchek.wp.readmore.security.CurrentUser;
import com.panchek.wp.readmore.security.UserPrincipal;
import com.panchek.wp.readmore.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.LineNumberInputStream;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

        @Autowired
        BookServiceImpl bookService;

    @GetMapping("/genre/{genre}")
    public List<BookReturn> getBooksByGenre(@CurrentUser UserPrincipal currentUser, @PathVariable(value="genre") String genre){
        return bookService.listBooksByGenre(currentUser, genre.trim().toLowerCase());
    }

    @GetMapping("/search/{searchword}")
    public List<BookReturn> searchBooks(@CurrentUser UserPrincipal currentUser,@PathVariable(value="searchword") String searchword){
        return bookService.searchBook(currentUser,searchword.trim().toLowerCase());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable(value="bookId") Long bookId){
        bookService.deleteBook(bookId);
        return ResponseEntity.ok(new ApiResponse(true,"Book deleted successfully!"));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBook(@Valid @RequestBody BookCreation bookCreation){
        if(bookService.existsBook(bookCreation.getName().trim().toLowerCase())){
            return new ResponseEntity(new ApiResponse(false,"A book with that title already exists!"),
                    HttpStatus.BAD_REQUEST);
        }
        Book result=bookService.createBook(bookCreation);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/book/{bookName}")
                .buildAndExpand(result.getName()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "Book created successfully!"));
    }

    @GetMapping("/")
    public BookReturn getBook(@CurrentUser UserPrincipal currentUser, @RequestParam String bookName){
        return bookService.findBookByName(currentUser, bookName.trim().toLowerCase());
    }

    @GetMapping("/popular")
    public List<BookReturn> getPopularBooks(@CurrentUser UserPrincipal currentUser){
        return bookService.listPopularBooks(currentUser);
    }

    @GetMapping("/author/{authorName}")
    public List<BookReturn> getBooksByAuthor(@CurrentUser UserPrincipal currentUser, @PathVariable(value="authorName")String authorName){
        return bookService.listBookByAuthor(currentUser, authorName.trim().toLowerCase());
    }

    @GetMapping("/series/{seriesName}")
    public List<BookReturn> getBooksBySeries(@CurrentUser UserPrincipal currentUser, @PathVariable(value="seriesName")String seriesName){
        return bookService.listBooksBySeries(currentUser, seriesName.trim().toLowerCase());
    }


}
