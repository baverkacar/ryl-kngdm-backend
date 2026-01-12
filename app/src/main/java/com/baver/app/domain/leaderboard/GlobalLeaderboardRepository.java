package com.baver.app.domain.leaderboard;

import java.util.List;

public interface GlobalLeaderboardRepository {
    List<LeaderboardEntry> getTopPlayers(int limit);
}
