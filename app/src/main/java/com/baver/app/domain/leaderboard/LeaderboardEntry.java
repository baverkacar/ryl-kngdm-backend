package com.baver.app.domain.leaderboard;

public record LeaderboardEntry(
        String publicId,
        String username,
        String country,
        int level
) {}
