package com.panchek.wp.readmore.controller;

import com.panchek.wp.readmore.model.Book;
import com.panchek.wp.readmore.payload.ApiResponse;
import com.panchek.wp.readmore.payload.BookCreation;
import com.panchek.wp.readmore.payload.BookReturn;
import com.panchek.wp.readmore.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

        @Autowired
        BookServiceImpl bookService;

    @GetMapping()
    public List<BookReturn> getBooksByGenre(@RequestParam String genre){
        return bookService.listBooksByGenre(genre);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBook(@Valid @RequestBody BookCreation bookCreation){
        if(bookService.existsBook(bookCreation.getName())){
            return new ResponseEntity(new ApiResponse(false,"A book with that title already exists!"),
                    HttpStatus.BAD_REQUEST);
        }
        Book result=bookService.createBook(bookCreation);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/book/{bookName}")
                .buildAndExpand(result.getName()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "Book created successfully!"));
    }
}
