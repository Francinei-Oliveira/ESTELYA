package com.estelya.auth.api;

import com.estelya.user.api.UserResponse;

public record LoginResponse(
        String accessToken,
        String tokenType,
        long expiresIn,
        UserResponse user
) {
}