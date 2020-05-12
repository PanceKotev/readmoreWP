package com.panchek.wp.readmore.controller;

import com.panchek.wp.readmore.exception.ResourceNotFoundException;
import com.panchek.wp.readmore.model.Role;
import com.panchek.wp.readmore.model.User;
import com.panchek.wp.readmore.payload.*;
import com.panchek.wp.readmore.repository.BookRepository;
import com.panchek.wp.readmore.repository.UserRepository;
import com.panchek.wp.readmore.security.CurrentUser;
import com.panchek.wp.readmore.security.UserPrincipal;
import com.panchek.wp.readmore.service.ReviewService;
import com.panchek.wp.readmore.service.impl.BookServiceImpl;
import com.panchek.wp.readmore.service.impl.LikeServiceImpl;
import com.panchek.wp.readmore.service.impl.ReviewServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LikeServiceImpl likeService;
    @Autowired
    private BookRepository bookRepository;

    private static final Logger logger= LoggerFactory.getLogger(UserController.class);

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser){
        User u = userRepository.getOne(currentUser.getId());
        Role role= (Role) u.getRoles().toArray()[0];
        String rolename= role.getName().name();
        return new UserSummary(currentUser.getId(),currentUser.getUsername(),currentUser.getName(),rolename);
    }

    @GetMapping("/checkUsernameAvailability")
    public UserIdentityAvailability checkUsername(@RequestParam String username){
        Boolean available=!userRepository.existsByUsername(username);
        return new UserIdentityAvailability(available);
    }
    @GetMapping("/checkEmailAvailability")
    public UserIdentityAvailability checkEmail(@RequestParam String email){
        Boolean available=!userRepository.existsByEmail(email);
        return new UserIdentityAvailability(available);
    }

    @GetMapping("/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username){
        User user=userRepository.findByUsername(username).orElseThrow(()->new ResourceNotFoundException("User","Username",username));
        Role role= (Role) user.getRoles().toArray()[0];
        String rolename= role.getName().name();
        UserProfile userProfile=new UserProfile(
                user.getId(),user.getUsername(),user.getName(),user.getCreatedAt(),
                user.getLikedBooks().stream().map(book ->{
                        boolean likedBy = bookRepository.bookLikedBy(user.getId(),book.getId());
                    return BookServiceImpl.mapBookToBR(book,likedBy);}
                ).collect(Collectors.toList()),
                user.getReviews().stream().map(review -> {
                    return ReviewServiceImpl.mapReviewToRR(review,false);
                }).collect(Collectors.toList()),
               rolename
                );

        return userProfile;
    }
    @GetMapping("/like/{bookId}")
    public BookReturn likeBook(@CurrentUser UserPrincipal currentUser, @PathVariable(value="bookId") Long bookId){
        return likeService.likeBook(currentUser,bookId);
    }
    @GetMapping("/unlike/{bookId}")
    public BookReturn unlikeBook(@CurrentUser UserPrincipal currentUser, @PathVariable(value="bookId") Long bookId){
        return likeService.unlikeBook(currentUser,bookId);
    }

}
