package com.soo.springsecu.responses;

import lombok.Builder;
import lombok.Getter;

@Builder
public class LoginResponse {
    @Getter
    private String token;

    private long expiresIn;
}
