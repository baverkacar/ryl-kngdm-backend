package com.baver.app.api.rest.dto;

public record CreateUserResponse(
        String userId,
        int level,
        int coins,
        String country
) {
}
