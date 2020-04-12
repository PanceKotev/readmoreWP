package com.panchek.wp.readmore.controller;

import com.panchek.wp.readmore.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/genre")
public class GenreController {
    @Autowired
    GenreRepository genreRepository;

    @GetMapping("/all")
    public List<String> getAll(){
        return genreRepository.findAll().stream().map(genre-> genre.getName()).collect(Collectors.toList());
    }
}
