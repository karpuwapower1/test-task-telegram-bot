package com.test.task.telegram.service.impl;

import com.test.task.telegram.dto.AdviceDto;
import com.test.task.telegram.service.AdviceService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TelegramBotService extends TelegramWebhookBot {

    private final AdviceService adviceService;

    @Value("${telegram.bot.user.name}")
    private String userName;

    @Value("${telegram.bot.token}")
    private String token;

    @Value("${telegram.bot.path}")
    private String path;

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        val cityName = update.getMessage().getText();
        val response = adviceService.getAllByCityName(cityName)
            .stream().map(AdviceDto::getDescription)
            .collect(Collectors.joining());
        val sendMessage = new SendMessage();
        sendMessage.setText(response);
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        return sendMessage;
    }

    @Override
    public String getBotPath() {
        return path;
    }
}
