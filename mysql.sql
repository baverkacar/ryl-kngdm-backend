CREATE TABLE users (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       public_id CHAR(9) NOT NULL,
                       country_code CHAR(2) NOT NULL,
                       level INT NOT NULL DEFAULT 1,
                       coins INT NOT NULL DEFAULT 5000,
                       version BIGINT NOT NULL DEFAULT 0,
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
                           ON UPDATE CURRENT_TIMESTAMP,

                       CONSTRAINT uk_users_public_id UNIQUE (public_id),
                       CONSTRAINT chk_country_code
                           CHECK (country_code IN ('TR', 'US', 'UK', 'FR', 'DE'))
);
