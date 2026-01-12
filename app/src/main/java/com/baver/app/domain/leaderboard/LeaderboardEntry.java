package com.baver.app.domain.leaderboard;

public record LeaderboardEntry(
        String publicId,
        String username,
        int level
) {}
