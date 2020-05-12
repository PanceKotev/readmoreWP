package com.panchek.wp.readmore.service.impl;

import com.panchek.wp.readmore.exception.AppException;
import com.panchek.wp.readmore.model.Role;
import com.panchek.wp.readmore.model.RoleName;
import com.panchek.wp.readmore.model.User;
import com.panchek.wp.readmore.payload.SignUpRequest;
import com.panchek.wp.readmore.repository.RoleRepository;
import com.panchek.wp.readmore.repository.UserRepository;
import com.panchek.wp.readmore.security.UserPrincipal;
import com.panchek.wp.readmore.service.CreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
public class CreationServiceImpl implements CreationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserPrincipal createUser(SignUpRequest signUpRequest) {
        User user=new User(signUpRequest.getName(),signUpRequest.getUsername(),signUpRequest.getEmail(),signUpRequest.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole=roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(
                ()->new AppException(("User role not set"))
        );
        user.setRoles(Collections.singleton(userRole));
        User result=userRepository.save(user);
        return UserPrincipal.create(result);
    }

    @Override
    @Transactional
    public UserPrincipal createAdmin(SignUpRequest signUpRequest) {
        User user=new User(signUpRequest.getName(),signUpRequest.getUsername(),signUpRequest.getEmail(),signUpRequest.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole=roleRepository.findByName(RoleName.ROLE_ADMIN).orElseThrow(
                ()->new AppException(("User role not set"))
        );
        user.setRoles(Collections.singleton(userRole));
        User result=userRepository.save(user);
        return UserPrincipal.create(result);
    }
}
