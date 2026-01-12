package com.baver.app.api.rest.dto;

import com.baver.app.domain.leaderboard.LeaderboardEntry;

import java.util.List;

public record GlobalLeaderboardResponse(
        List<LeaderboardEntry> leaderboard
) {}