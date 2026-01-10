package com.baver.app.infrastructure.config;

import com.baver.app.application.user.PublicIdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneratorConfig {

    /**
     * Public ID generator bean. Created for:
     *
     * <br> - To manage its lifecycle via Spring.
     * <br> - To allow easy injection into other components.
     *
     * @return PublicIdGenerator instance
     */
    @Bean
    public PublicIdGenerator publicIdGenerator() {
        return new PublicIdGenerator();
    }
}
