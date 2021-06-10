package com.test.task.telegram.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

import java.util.Collections;

@Configuration
public class SetWebhookConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public String ngrokProxyUrl(RestTemplate restTemplate,
                                @Value("${ngrok.url}") String ngrokUrl,
                                @Value("${ngrok.created_url_parameter}") String createdUrlParameter) {
        val response = restTemplate.exchange(ngrokUrl, HttpMethod.GET,
            getNgrokUrlHttpRequestEntity(), String.class);
        return new Gson().fromJson(response.getBody(), JsonObject.class).get(createdUrlParameter).getAsString();
    }

    @Bean
    public SetWebhook setWebhook(String ngrokProxyUrl,
                                 @Value("${telegram.bot.webhook_register_root_url}") String webhookUrl) {
        registerTelegramWebhookUrl(restTemplate(), ngrokProxyUrl, webhookUrl);
        return SetWebhook.builder()
            .url(ngrokProxyUrl)
            .build();
    }

    private void registerTelegramWebhookUrl(RestTemplate restTemplate,
                                            String ngrokProxyUrl,
                                            String webhookUrl) {
        val resp = restTemplate.getForEntity(createWebHookRegisterUrl(ngrokProxyUrl, webhookUrl),
            String.class);
        if (resp.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Can not register telegram webhook");
        }
    }

    private HttpEntity<String> getNgrokUrlHttpRequestEntity() {
        return new HttpEntity<>("body", createNgrokUrlHttpHeaders());
    }

    private HttpHeaders createNgrokUrlHttpHeaders() {
        val headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }

    private String createWebHookRegisterUrl(String ngrokProxyUrl, String webhookRegisterUrl) {
        return UriComponentsBuilder.fromUriString(webhookRegisterUrl)
            .queryParam("url", ngrokProxyUrl)
            .toUriString();
    }
}
