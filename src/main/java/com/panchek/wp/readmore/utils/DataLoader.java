package com.panchek.wp.readmore.utils;

import com.panchek.wp.readmore.payload.BookCreation;
import com.panchek.wp.readmore.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private BookServiceImpl bookService;

    @Autowired
    public DataLoader(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    public void run(ApplicationArguments args) {
        /*List<String> genres=new ArrayList<String>();
        genres.add("Philosophy");
        bookService.createBook(new BookCreation("Think","Philosophy book",
                genres,"https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRO0I8VktcWPhEIfyOHMJCD8wGa5oUqV0OxWBHQoGRq-0MnBrVx",
                "Simon Blackburn",LocalDate.of(2005,2,3),332,"English","idk","Thinker"
                ));
        genres.clear();
        genres.add("fantasy");
        genres.add("fiction");
        genres.add("Young-adult");
        bookService.createBook(new BookCreation("Name of the wind","Magical story about the name of the wind.",
                genres,"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQu6wFUsbfcEs0v9DFHElYEAZvzd6GbtdaWP4NSM-JmUN_557A4",
                "Patrick Roothfuss",LocalDate.of(2007,3,27),662,"English","idk","The name of the wind"
        ));
        bookService.createBook(new BookCreation("Wise man's fear","Magical story about the wise mans fear.",
                genres,"https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcSo4HCrzrPG99EyD3sZRaVy1pgVy-L6ZVuElINJ-tmTaMF3VFEY",
                "Patrick Roothfuss",LocalDate.of(2009,6,22),1242,"English","idk","The name of the wind"
        ));
        genres.clear();
        genres.add("horror");
        genres.add("thriller");
        bookService.createBook(new BookCreation("It","A story about a clwown.",
                genres,"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcRbVZgRR65QJ_Cad7wANF4kseUyFhciIHEI717kL6C9E4xqZ1bo",
                "Stephen King",LocalDate.of(1994,9,15),800,"English","idk",""
        ));
        genres.clear();
        genres.add("epic fantasy");
        genres.add("fiction");
        bookService.createBook(new BookCreation("The fellowship of the ring","The narrative follows on from The Hobbit, in which the hobbit Bilbo Baggins finds the Ring, which had been in the possession of the creature Gollum.",
                genres,"https://images-na.ssl-images-amazon.com/images/I/51tW-UJVfHL._SX321_BO1,204,203,200_.jpg",
                "J.R.R. Tolkien",LocalDate.of(1954,7,29),800,"English","idk","The lord of the rings"
        ));
        bookService.createBook(new BookCreation("The two towers","Uruk-hai sent by Saruman and other Orcs sent by Sauron kill Boromir and capture Merry and Pippin. Aragorn, Gimli and Legolas debate which pair of hobbits to follow.",
                genres,"https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRDuJkPxiesiukgaKqock6EAf8TdK3boG68LlSoI3DZXLJMfj-u",
                "J.R.R. Tolkien",LocalDate.of(1954,11,11),800,"English","idk","The lord of the rings"
        ));*/
    }
}