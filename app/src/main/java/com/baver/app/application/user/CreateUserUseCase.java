package com.baver.app.application.user;

import com.baver.app.api.rest.dto.CreateUserRequest;
import com.baver.app.api.rest.dto.UserResponse;

public interface CreateUserUseCase {
    UserResponse createUser(CreateUserRequest request);
}
