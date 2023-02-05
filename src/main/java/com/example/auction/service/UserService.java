package com.example.auction.service;

import com.example.auction.model.dto.AuthRequestDto;
import com.example.auction.model.dto.AuthResponseDto;
import com.example.auction.model.dto.RegisterDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    String registerUser(RegisterDto registerDto);
    AuthResponseDto login(AuthRequestDto authRequestDto);
}
