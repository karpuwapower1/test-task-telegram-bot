package com.test.task.telegram.controller;

import com.test.task.telegram.service.TelegramBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TelegramController {

    private final TelegramBotService telegramBotService;

    @PostMapping
    public BotApiMethod<? extends Serializable> onUpdateReceived(@RequestBody Update update) {
        return telegramBotService.onWebhookUpdateReceived(update);
    }
}
