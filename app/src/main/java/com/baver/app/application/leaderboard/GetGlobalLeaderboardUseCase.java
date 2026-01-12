package com.baver.app.application.leaderboard;

import com.baver.app.api.rest.dto.GlobalLeaderboardResponse;
import com.baver.app.domain.leaderboard.GlobalLeaderboardRepository;
import com.baver.app.domain.leaderboard.LeaderboardEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetGlobalLeaderboardUseCase {

    private final GlobalLeaderboardRepository globalLeaderboardRepository;

    public GlobalLeaderboardResponse getGlobalLeaderboard(int limit) {

        List<LeaderboardEntry> entries = globalLeaderboardRepository.getTopPlayers(limit);
        return new GlobalLeaderboardResponse(entries);
    }
}
