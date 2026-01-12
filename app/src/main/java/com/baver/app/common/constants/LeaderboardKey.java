package com.baver.app.common.constants;

import lombok.Getter;

@Getter
public enum LeaderboardKey {

    GLOBAL("leaderboard:global");


    private final String keyTemplate;

    LeaderboardKey(String keyTemplate) {
        this.keyTemplate = keyTemplate;
    }

    public String getKey() {
        return this.keyTemplate;
    }

    public String getKey(Object... args) {
        return String.format(this.keyTemplate, args);
    }
}
