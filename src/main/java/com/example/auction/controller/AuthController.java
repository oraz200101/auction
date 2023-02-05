package com.example.auction.controller;

import com.example.auction.model.dto.AuthRequestDto;
import com.example.auction.model.dto.AuthResponseDto;
import com.example.auction.model.dto.RegisterDto;
import com.example.auction.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.auction.constants.swagger.UserSwaggerConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = NAME_OF_AUTH_SERVICE,description = DESCRIPTION_AUTH_CONTROLLER)
public class AuthController {

    private final UserServiceImpl userService;
    @PostMapping("register")
    @Operation(description = DESCRIPTION_OF_FOR_REGISTER)
    public ResponseEntity<String> registerUser(@RequestBody @Validated RegisterDto registerDto){
        return ResponseEntity.ok(userService.registerUser(registerDto));
    }
    @PostMapping("login")
    @Operation(description = DESCRIPTION_OF_FOR_LOGIN)
    public ResponseEntity<AuthResponseDto> login(@RequestBody @Validated AuthRequestDto authRequestDto){
        return ResponseEntity.ok(userService.login(authRequestDto));
    }
}
