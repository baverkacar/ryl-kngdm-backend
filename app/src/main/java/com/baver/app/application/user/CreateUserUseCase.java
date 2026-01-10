package com.baver.app.application.user;

import com.baver.app.api.rest.dto.CreateUserRequest;
import com.baver.app.api.rest.dto.CreateUserResponse;

public interface CreateUserUseCase {
    CreateUserResponse createUser(CreateUserRequest request);
}
