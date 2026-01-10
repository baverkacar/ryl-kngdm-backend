package com.baver.app.domain.user;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public enum CountryCode {
    TR,
    US,
    UK,
    FR,
    DE;

    private static final List<CountryCode> VALUES = List.of(values());

    public static CountryCode random() {
        return VALUES.get(ThreadLocalRandom.current().nextInt(VALUES.size()));
    }
}
