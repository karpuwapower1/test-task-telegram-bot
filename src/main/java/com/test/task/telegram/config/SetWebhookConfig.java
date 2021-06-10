package com.test.task.telegram.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
@Profile("!dev")
public class SetWebhookConfig {

    @Bean
    public SetWebhook setWebhook(@Value("${telegram.bot.path}") String path) {
        return SetWebhook.builder()
            .url(path)
            .build();
    }
}
