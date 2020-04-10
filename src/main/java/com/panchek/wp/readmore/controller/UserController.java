package com.panchek.wp.readmore.controller;

import com.panchek.wp.readmore.exception.ResourceNotFoundException;
import com.panchek.wp.readmore.model.User;
import com.panchek.wp.readmore.payload.UserIdentityAvailability;
import com.panchek.wp.readmore.payload.UserProfile;
import com.panchek.wp.readmore.payload.UserSummary;
import com.panchek.wp.readmore.repository.UserRepository;
import com.panchek.wp.readmore.security.CurrentUser;
import com.panchek.wp.readmore.security.UserPrincipal;
import com.panchek.wp.readmore.service.ReviewService;
import com.panchek.wp.readmore.service.impl.BookServiceImpl;
import com.panchek.wp.readmore.service.impl.ReviewServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    private static final Logger logger= LoggerFactory.getLogger(UserController.class);

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser){
        UserSummary userSummary=new UserSummary(currentUser.getId(),currentUser.getUsername(),currentUser.getName());
        return userSummary;
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
        UserProfile userProfile=new UserProfile(
                user.getId(),user.getUsername(),user.getName(),user.getCreatedAt(),
                user.getLikedBooks().stream().map(book -> BookServiceImpl.mapBookToBR(book)).collect(Collectors.toList()),
                user.getReviews().stream().map(review-> ReviewServiceImpl.mapReviewToRR(review)).collect(Collectors.toList())
                );

        return userProfile;
    }

}
