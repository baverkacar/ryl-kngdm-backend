package com.baver.app.api.rest;

import com.baver.app.api.rest.dto.CreateUserRequest;
import com.baver.app.api.rest.dto.CreateUserResponse;
import com.baver.app.application.user.CreateUserUseCase;
import com.baver.app.common.constants.ApiVersion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "User Management", description = "Operations related to user lifecycle")
public class UserController {

    private static final String HEADER_API_VERSION = "X-Api-Version";
    private static final String V1_HEADER_MATCH = HEADER_API_VERSION + "=1";
    
    private final CreateUserUseCase createUserUseCase;

    /**
     * Creates a new user with initial stats.
     * * @param request The user creation payload containing username
     * @return The created user with generated ID
     */
    @Operation(summary = "Create a new user (V1)", description = "Requires unique username and header X-Api-Version=1")
    @PostMapping(headers = ApiVersion.Constants.V1_MATCH)
    public ResponseEntity<CreateUserResponse> createUserV1(
            @Valid @RequestBody CreateUserRequest request
    ) {
        log.info("REQUEST_RECEIVED endpoint=CreateUser version=1 username={}", request.username());

        CreateUserResponse response = createUserUseCase.createUser(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
