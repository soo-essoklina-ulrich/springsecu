package com.soo.springsecu.controller;

import com.soo.springsecu.dtos.LoginUserDto;
import com.soo.springsecu.dtos.RegisterUserDto;
import com.soo.springsecu.entity.User;
import com.soo.springsecu.repository.UserRepository;
import com.soo.springsecu.responses.LoginResponse;
import com.soo.springsecu.services.AuthenticationService;
import com.soo.springsecu.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }


    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto input) {
        User registeruser = authenticationService.signup(input);
        return ResponseEntity.ok(registeruser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDto input) {
        User authenticateuser = authenticationService.authenticate(input);
        String token = jwtService.generateToken(authenticateuser);
        LoginResponse loginResponse = LoginResponse.builder()
                .token(token)
                .expiresIn(jwtService.getExpirationTime())
                .build();
        return ResponseEntity.ok(loginResponse);
    }

}
