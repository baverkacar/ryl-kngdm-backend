package com.baver.app.domain.user;


import java.time.Instant;
import java.util.Objects;

/**
 * Domain model representing a User.
 * This class is immutable; any state changes should be handled via methods that return new instances.
 */
public class User {
    private final Long id;
    private final String publicId;
    private final String username; // Kullanıcı adı eklendi (Entity ile uyum için)
    private final CountryCode countryCode;

    private final int level;
    private final int coins;

    private final long version;
    private final Instant createdAt;
    private final Instant updatedAt;

    // Constructor Private kalmalı!
    private User(
            Long id,
            String publicId,
            String username,
            CountryCode countryCode,
            int level,
            int coins,
            long version,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.id = id;
        this.publicId = Objects.requireNonNull(publicId, "publicId");
        this.username = Objects.requireNonNull(username, "username");
        this.countryCode = Objects.requireNonNull(countryCode, "countryCode");
        this.level = level;
        this.coins = coins;
        this.version = version;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // FACTORY 1: Business Logic
    public static User newUser(String publicId, String username, CountryCode countryCode, Instant now) {
        return new User(
                null,
                publicId,
                username,
                countryCode,
                1,
                5000,
                0L,
                now,
                now
        );
    }

    // --- FACTORY 2: VERİTABANINDAN YÜKLEME (Rehydrate) ---
    public static User load(
            Long id,
            String publicId,
            String username,
            CountryCode countryCode,
            int level,
            int coins,
            long version,
            Instant createdAt,
            Instant updatedAt
    ) {
        return new User(id, publicId, username, countryCode, level, coins, version, createdAt, updatedAt);
    }

    // Getters...
    public Long getId() { return id; }
    public String getPublicId() { return publicId; }
    public String getUsername() { return username; }
    public CountryCode getCountryCode() { return countryCode; }
    public int getLevel() { return level; }
    public int getCoins() { return coins; }
    public long getVersion() { return version; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
}
