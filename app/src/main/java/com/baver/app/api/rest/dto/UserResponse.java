package com.baver.app.api.rest.dto;

public record UserResponse(
        String userId,
        String username,
        int level,
        int coins,
        String country
) {
}
