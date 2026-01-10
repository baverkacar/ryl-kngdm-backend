package com.baver.app.common.constants;

public enum ApiVersion {
    V1(Constants.V1_VALUE);

    private final String value;

    ApiVersion(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static class Constants {
        public static final String HEADER_KEY = "X-Api-Version";
        public static final String V1_VALUE = "1";

        public static final String V1_MATCH = HEADER_KEY + "=" + V1_VALUE;
    }
}
