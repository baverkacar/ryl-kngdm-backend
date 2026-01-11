package com.baver.app.application.user;

import com.baver.app.api.rest.dto.CreateUserRequest;
import com.baver.app.api.rest.dto.UserResponse;
import com.baver.app.domain.user.CountryCode;
import com.baver.app.domain.user.User;
import com.baver.app.domain.user.UserExistsException;
import com.baver.app.infrastructure.persistence.jpa.UserRepository;
import com.baver.app.infrastructure.persistence.jpa.entity.UserEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements CreateUserUseCase {

    private static final int MAX_PUBLIC_ID_ATTEMPTS = 25;

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PublicIdGenerator publicIdGenerator;


    @Override
    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
        String username = request.username();
        log.info("CREATE_USER_STARTED username={}", request.username());

        if (userRepository.existsByUsername(username)) {
            log.warn("CREATE_USER_REJECTED reason=USERNAME_EXISTS username={}", username);
            throw new UserExistsException(username);
        }

        CountryCode countryCode = CountryCode.random();
        Instant now = Instant.now();
        String publicId = generateUniquePublicId();

        User domainUser = User.newUser(publicId, username, countryCode, now);
        UserEntity userEntity = userMapper.toEntity(domainUser);
        userRepository.save(userEntity);

        log.info("USER_CREATED userId={} username={} country={} level={} coins={}",
                domainUser.getPublicId(),
                domainUser.getUsername(),
                domainUser.getCountryCode().name(),
                domainUser.getLevel(),
                domainUser.getCoins()
        );

        return userMapper.toCreateUserResponse(domainUser) ; // map user : domainUser;
    }

    private String generateUniquePublicId() {
        for (int i = 0; i < MAX_PUBLIC_ID_ATTEMPTS; i++) {
            String candidate = publicIdGenerator.generate();
            if (!userRepository.existsByPublicId(candidate)) {
                return candidate;
            }
        }
        throw new RuntimeException();
    }
}
