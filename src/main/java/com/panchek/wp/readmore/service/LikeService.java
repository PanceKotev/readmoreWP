package com.panchek.wp.readmore.service;

import com.panchek.wp.readmore.model.Book;
import com.panchek.wp.readmore.payload.BookReturn;
import com.panchek.wp.readmore.security.UserPrincipal;

public interface LikeService {

    BookReturn likeBook(UserPrincipal currentUser, Long bookId);
    BookReturn unlikeBook(UserPrincipal currentUser,Long bookId);
}
