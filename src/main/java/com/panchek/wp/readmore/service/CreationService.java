package com.panchek.wp.readmore.service;

import com.panchek.wp.readmore.payload.SignUpRequest;
import com.panchek.wp.readmore.security.UserPrincipal;

public interface CreationService {

    UserPrincipal createUser(SignUpRequest signUpRequest);

    UserPrincipal createAdmin(SignUpRequest signUpRequest);
}
