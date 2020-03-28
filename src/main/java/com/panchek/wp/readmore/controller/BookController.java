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
import java.io.LineNumberInputStream;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

        @Autowired
        BookServiceImpl bookService;

    @GetMapping("/genre/{genre}")
    public List<BookReturn> getBooksByGenre(@PathVariable(value="genre") String genre){
        return bookService.listBooksByGenre(genre.trim().toLowerCase());
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
    public BookReturn getBook(@RequestParam String bookName){
        return bookService.findBookByName(bookName.trim().toLowerCase());
    }

    @GetMapping("/author/{authorName}")
    public List<BookReturn> getBooksByAuthor(@PathVariable(value="authorName")String authorName){
        return bookService.listBookByAuthor(authorName.trim().toLowerCase());
    }

    @GetMapping("/series/{seriesName}")
    public List<BookReturn> getBooksBySeries(@PathVariable(value="seriesName")String seriesName){
        return bookService.listBooksBySeries(seriesName.trim().toLowerCase());
    }


}
