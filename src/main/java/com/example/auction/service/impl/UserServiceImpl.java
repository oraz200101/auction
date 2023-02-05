package com.example.auction.service.impl;

import com.example.auction.exception.domain.UserNotFoundByEmailException;
import com.example.auction.jwt.JwtGenerator;
import com.example.auction.model.dto.AuthRequestDto;
import com.example.auction.model.dto.AuthResponseDto;
import com.example.auction.model.dto.RegisterDto;
import com.example.auction.model.entity.User;
import com.example.auction.model.enums.Role;
import com.example.auction.repository.UserRepository;
import com.example.auction.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

import static com.example.auction.constants.validation.UserValidationConstants.USER_EMAIL_ALREADY_EXISTS_MESSAGE;
import static com.example.auction.constants.validation.UserValidationConstants.USER_PASSWORDS_NOT_EQUAL_MESSAGE;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;
    private final AuthenticationManager authenticationManager;
    private final MailServiceImpl mailService;

    public UserServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder,JwtGenerator jwtGenerator,
                           @Lazy AuthenticationManager authenticationManager,MailServiceImpl mailService
                           ) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtGenerator=jwtGenerator;
        this.authenticationManager=authenticationManager;
        this.mailService=mailService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return userRepository.getByEmail(email).orElseThrow(UserNotFoundByEmailException::new);
    }

    @Override
    @Transactional
    public String registerUser(RegisterDto registerDto) {
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new RuntimeException( USER_EMAIL_ALREADY_EXISTS_MESSAGE);
        }
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())){
            throw new RuntimeException(USER_PASSWORDS_NOT_EQUAL_MESSAGE);
        }
        User user=new User();
        user.setRoles(new HashSet<>());
        user.getRoles().add(Role.USER);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        userRepository.save(user);
        String message =String.format(
                "Hello, "+user.getName() +
                        "\n + Welcome to Auction"
        );
        mailService.send(user.getEmail(),"Welcome to Auction",message);
        return "Register is success";
    }

    @Override
    public AuthResponseDto login(AuthRequestDto authRequestDto) {
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDto.getEmail(),
                        authRequestDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtGenerator.generateToken(authentication);
        return new AuthResponseDto(token);
    }


}
