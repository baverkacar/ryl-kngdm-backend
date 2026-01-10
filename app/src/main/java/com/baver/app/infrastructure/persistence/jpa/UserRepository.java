package com.baver.app.infrastructure.persistence.jpa;

import com.baver.app.infrastructure.persistence.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>  {
    boolean existsByUsername(String username);
    boolean existsByPublicId(String publicId);
}
