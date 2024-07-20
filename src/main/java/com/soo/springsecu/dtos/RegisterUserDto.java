package com.soo.springsecu.dtos;

public record RegisterUserDto(
        String email,
        String username,
        String password
) {
}
