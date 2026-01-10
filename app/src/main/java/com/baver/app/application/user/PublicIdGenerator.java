package com.baver.app.application.user;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Responsible for generating human-readable public IDs. *
 */
public class PublicIdGenerator {

    public String generate() {
        return createRandomLetters(3) + createRandomDigits(6);
    }

    private String createRandomLetters(int n) {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            char c = (char) ('A' + r.nextInt(26));
            sb.append(c);
        }
        return sb.toString();
    }

    private String createRandomDigits(int n) {
        int bound = 1;
        for (int i = 0; i < n; i++) bound *= 10;

        int value = ThreadLocalRandom.current().nextInt(bound);
        return String.format("%0" + n + "d", value);
    }
}
