package com.panchek.wp.readmore.utils;

import com.panchek.wp.readmore.payload.BookCreation;
import com.panchek.wp.readmore.payload.ReviewRequest;
import com.panchek.wp.readmore.payload.ReviewResponse;
import com.panchek.wp.readmore.payload.SignUpRequest;
import com.panchek.wp.readmore.security.UserPrincipal;
import com.panchek.wp.readmore.service.impl.BookServiceImpl;
import com.panchek.wp.readmore.service.impl.CreationServiceImpl;
import com.panchek.wp.readmore.service.impl.LikeServiceImpl;
import com.panchek.wp.readmore.service.impl.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private BookServiceImpl bookService;

    private CreationServiceImpl creationService;

    private ReviewServiceImpl reviewService;

    private LikeServiceImpl likeService;

    @Autowired
    public DataLoader(BookServiceImpl bookService, CreationServiceImpl creationService,ReviewServiceImpl reviewService, LikeServiceImpl likeService) {
        this.bookService = bookService;
        this.creationService = creationService;
        this.reviewService = reviewService;
        this.likeService = likeService;
    }

    @Transactional
    public void run(ApplicationArguments args) {
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setName("User 1");
        signUpRequest.setUsername("User1");
        signUpRequest.setPassword("password");
        signUpRequest.setEmail("user@example.com");
        UserPrincipal user1=creationService.createUser(signUpRequest);
        signUpRequest.setName("Administrator");
        signUpRequest.setUsername("Admin");
        signUpRequest.setEmail("admin@example.com");
        UserPrincipal admin = creationService.createAdmin(signUpRequest);
        signUpRequest.setName("User 2");
        signUpRequest.setUsername("User2");
        signUpRequest.setPassword("password");
        signUpRequest.setEmail("user2@example.com");
        UserPrincipal user2=creationService.createUser(signUpRequest);

        List<String> genres=new ArrayList<String>();

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
        ));

        genres.clear();
        genres.add("Science Fiction");
        genres.add("Social Fantasy");
        bookService.createBook(new BookCreation("1984","Nineteen Eighty-Four: A Novel, often published as 1984, is a dystopian novel by English novelist George Orwell.",
                genres,"https://i.pinimg.com/736x/cf/d6/3f/cfd63f6efbeacf66b486e274f2eb19a0.jpg",
                "George Orwell",LocalDate.of(1949,6,8),328,"English","idk",""
        ));

        genres.clear();
        genres.add("Science Fiction");
        bookService.createBook(new BookCreation("Stranger in a Strange Land","Stranger in a Strange Land is a 1961 science fiction novel by American author Robert A. Heinlein. ",
                genres,"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQxwjJLPypipe9Nm8KRQLO_-O-JvnbR9dCrkF2KAb-_IL6r-ndX",
                "Robert A. Heinlein",LocalDate.of(1961,6,1),408,"English","idk",""
        ));
        /*
        ReviewRequest reviewRequest=new ReviewRequest(Long.parseLong("1"),"Good book, I liked it", 3.5);
        reviewService.createReview(user1,reviewRequest);
        reviewRequest=new ReviewRequest(Long.parseLong("1"),"Didn't like it that much",1.5);
        reviewService.createReview(admin,reviewRequest);
        reviewRequest=new ReviewRequest(Long.parseLong("3"),"I liked the writing in this",4.0);
        reviewService.createReview(admin,reviewRequest);
        reviewRequest=new ReviewRequest(Long.parseLong("2"),"Excellent book!",5.0);
        reviewService.createReview(admin,reviewRequest);
        reviewRequest=new ReviewRequest(Long.parseLong("3"),"Didn't like it",2.0);
        reviewService.createReview(user2,reviewRequest);
        reviewRequest=new ReviewRequest(Long.parseLong("5"),"I liked the book, good job!",4.0);
        reviewService.createReview(user2,reviewRequest);

        likeService.likeBook(admin,Long.parseLong("3"));
        likeService.likeBook(user1,Long.parseLong("4"));
        likeService.likeBook(user2,Long.parseLong("1"));
        likeService.likeBook(admin,Long.parseLong("5"));
        likeService.likeBook(user1,Long.parseLong("2"));*/

    }
}