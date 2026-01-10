package com.baver.app.application.user;

import com.baver.app.api.rest.dto.CreateUserResponse;
import com.baver.app.domain.user.User;
import com.baver.app.infrastructure.persistence.jpa.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserEntity toEntity(User user) {
        if (user == null) return null;

        UserEntity e = new UserEntity();

        e.setPublicId(user.getPublicId());
        e.setUsername(user.getUsername());
        e.setCountryCode(user.getCountryCode());
        e.setLevel(user.getLevel());
        e.setCoins(user.getCoins());

        return e;
    }

    public CreateUserResponse toCreateUserResponse(User user) {
        if (user == null) return null;

        return new CreateUserResponse(
                user.getPublicId(),
                user.getLevel(),
                user.getCoins(),
                user.getCountryCode().name()
        );
    }
}
