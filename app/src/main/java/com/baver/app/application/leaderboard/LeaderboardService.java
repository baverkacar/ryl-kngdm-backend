package com.baver.app.application.leaderboard;

import com.baver.app.api.rest.dto.GlobalLeaderboardResponse;
import com.baver.app.domain.user.CountryCode;

public interface LeaderboardService {
    GlobalLeaderboardResponse getGlobalLeaderboard(int limit);
    GlobalLeaderboardResponse getCountryLeaderboard(CountryCode countryCode, int limit);
}
