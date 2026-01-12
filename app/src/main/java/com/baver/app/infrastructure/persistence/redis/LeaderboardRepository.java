package com.baver.app.infrastructure.persistence.redis;

import com.baver.app.common.constants.LeaderboardKey;
import com.baver.app.domain.leaderboard.LeaderboardEntry;
import com.baver.app.domain.user.CountryCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class LeaderboardRepository implements com.baver.app.domain.leaderboard.LeaderboardRepository {

    private final StringRedisTemplate redisTemplate;

    @Override
    public List<LeaderboardEntry> getTopPlayers(int limit) {
        String key = LeaderboardKey.GLOBAL.getKey();

        return getLeaderboardEntries(limit, key);
    }

    @Override
    public List<LeaderboardEntry> getTopPlayersByCountry(CountryCode countryCode, int limit) {
        String key = LeaderboardKey.COUNTRY.getKey(countryCode.name());

        return getLeaderboardEntries(limit, key);
    }

    private List<LeaderboardEntry> getLeaderboardEntries(int limit, String key) {
        Set<ZSetOperations.TypedTuple<String>> range = redisTemplate.opsForZSet()
                .reverseRangeWithScores(key, 0, limit - 1);

        if (range == null || range.isEmpty()) {
            return Collections.emptyList();
        }

        return range.stream()
                .map(this::mapToEntry)
                .filter(Objects::nonNull)
                .toList();
    }

    private LeaderboardEntry mapToEntry(ZSetOperations.TypedTuple<String> tuple) {
        String value = tuple.getValue();
        Double score = tuple.getScore();

        if (value == null || score == null) {
            return null;
        }

        String[] parts = value.split(":");

        if (parts.length < 3) {
            return null;
        }

        return new LeaderboardEntry(
                parts[0],
                parts[1],
                score.intValue()
        );
    }
}
