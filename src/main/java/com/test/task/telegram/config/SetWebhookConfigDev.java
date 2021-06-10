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
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

import java.util.Collections;

@Configuration
@Profile("dev")
public class SetWebhookConfigDev {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public String ngrokProxyUrl(RestTemplate restTemplate,
                                @Value("${ngrok.url}") String ngrokUrl,
                                @Value("${ngrok.created_url_parameter}") String createdUrlParameter) {
        System.out.println("n\n\n\n" + ngrokUrl + "\n\n\n\n");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        val response = restTemplate.exchange(ngrokUrl, HttpMethod.GET, entity, String.class);
//        val response = restTemplate.getForEntity(ngrokUrl, String.class).getBody();
        System.out.println("n\n\n\n" + response + "\n\n\n\n");
        return new Gson().fromJson(response.getBody(), JsonObject.class).get(createdUrlParameter).getAsString();
    }

    @Bean
    public SetWebhook setWebhook(String ngrokProxyUrl,
                                 @Value("${telegram.bot.webhook_register_root_url}") String webhookUrl) {
        registerTelegramWebhookUrl(restTemplate(), ngrokProxyUrl, webhookUrl);
        System.out.println("/n/n/n/n" + "ngrokProxyUrl     " + ngrokProxyUrl + "/n/n/n/n");
        return SetWebhook.builder()
            .url(ngrokProxyUrl)
            .build();
    }

    private void registerTelegramWebhookUrl(RestTemplate restTemplate,
                                            String ngrokProxyUrl,
                                            String webhookUrl) {
        val webHookRegisterUrl = createWebHookRegisterUrl(ngrokProxyUrl, webhookUrl);
        val resp = restTemplate.getForEntity(webHookRegisterUrl, String.class);
        System.out.println("\n\n\n\n" + resp.toString() + "\n\n\n\n");
        if (resp.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Can not register telegram webhook");
        }
    }

    private String createWebHookRegisterUrl(String ngrokProxyUrl, String webhookRegisterUrl) {
        return UriComponentsBuilder.fromUriString(webhookRegisterUrl)
            .queryParam("url", ngrokProxyUrl)
            .toUriString();
    }
}
