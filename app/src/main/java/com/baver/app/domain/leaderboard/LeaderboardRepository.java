package com.baver.app.domain.leaderboard;

import com.baver.app.domain.user.CountryCode;

import java.util.List;

public interface LeaderboardRepository {
    List<LeaderboardEntry> getTopPlayers(int limit);
    List<LeaderboardEntry> getTopPlayersByCountry(CountryCode countryCode, int limit);
}
