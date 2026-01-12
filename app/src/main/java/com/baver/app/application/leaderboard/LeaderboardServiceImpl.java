package com.baver.app.application.leaderboard;

import com.baver.app.api.rest.dto.GlobalLeaderboardResponse;
import com.baver.app.domain.leaderboard.LeaderboardRepository;
import com.baver.app.domain.leaderboard.LeaderboardEntry;
import com.baver.app.domain.user.CountryCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LeaderboardServiceImpl implements LeaderboardService {

    private final LeaderboardRepository leaderboardRepository;

    public GlobalLeaderboardResponse getGlobalLeaderboard(int limit) {

        List<LeaderboardEntry> entries = leaderboardRepository.getTopPlayers(limit);
        return new GlobalLeaderboardResponse(entries);
    }

    @Override
    public GlobalLeaderboardResponse getCountryLeaderboard(CountryCode countryCode, int limit) {
        log.info("USE_CASE_STARTED operation=GetCountryLeaderboard country={} limit={}", countryCode, limit);

        List<LeaderboardEntry> topPlayers = leaderboardRepository.getTopPlayersByCountry(countryCode, limit);

        log.info("USE_CASE_COMPLETED retrieved_count={}", topPlayers.size());

        return new GlobalLeaderboardResponse(topPlayers);
    }
}
