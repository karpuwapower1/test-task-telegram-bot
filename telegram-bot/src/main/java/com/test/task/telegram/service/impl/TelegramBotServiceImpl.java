package com.test.task.telegram.service.impl;

import com.test.task.telegram.exception.NoSuchCityException;
import com.test.task.telegram.service.CityService;
import com.test.task.telegram.service.TelegramBotService;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;

@Service
public class TelegramBotServiceImpl extends SpringWebhookBot implements TelegramBotService {

    private static final String ERROR_MESSAGE_TEMPLATE = "Для города %s данных нет";

    private final CityService cityService;

    @Value("${telegram.bot.user.name}")
    private String userName;
    @Value("${telegram.bot.token}")
    private String token;

    public TelegramBotServiceImpl(CityService cityService, SetWebhook setWebhook) {
        super(setWebhook);
        this.cityService = cityService;
    }

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotPath() {
        return super.getSetWebhook().getUrl();
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return (update.getMessage() != null && update.getMessage().getText() != null)
            ? processMessage(update.getMessage().getText(), update.getMessage().getChatId())
            : new SendMessage();
    }

    private BotApiMethod<?> processMessage(String cityName, Long chatId) {
        try {
            val text = cityService.getCityByName(cityName).getInfo();
            return createSendMessage(chatId, text);
        } catch (NoSuchCityException e) {
            return createSendMessage(chatId, String.format(ERROR_MESSAGE_TEMPLATE, cityName));
        }
    }

    private SendMessage createSendMessage(Long chatId, String text) {
        return SendMessage.builder()
            .text(text)
            .chatId(String.valueOf(chatId))
            .allowSendingWithoutReply(true)
            .build();
    }
}
