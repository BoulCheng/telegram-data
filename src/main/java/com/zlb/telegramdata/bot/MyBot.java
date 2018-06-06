package com.zlb.telegramdata.bot;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class MyBot extends TelegramLongPollingBot {

    private static final Logger TELEGRAM_LOG = LoggerFactory.getLogger(MyBot.class);
    private static final Logger TELEGRAM_ERROR_LOG = LoggerFactory.getLogger(Constant.TELEGRAM_ERROR_LOG);

    @Override
    public String getBotToken() {
        return Constant.BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage()) {
            if (CollectionUtils.isNotEmpty(update.getMessage().getNewChatMembers())) {
                Long chatId;
                String text;
                for (User user : update.getMessage().getNewChatMembers()) {
                    // Create a SendMessage object with mandatory fields
                    chatId = update.getMessage().getChatId();
                    text = Constant.WELCOME_TEXT_DEFAULT;
                    try {
                        text = String.format(Constant.WELCOME_TEXT_DEFAULT, ((StringUtils.isNotBlank(user.getFirstName()) ? user.getFirstName() : "") + " " + (StringUtils.isNotBlank(user.getLastName()) ? user.getLastName() : "")).trim());
                    } catch (Exception e) {
                        e.printStackTrace();
                        TELEGRAM_ERROR_LOG.error("format error:", e);
                        continue;
                    }
                    SendMessage message = new SendMessage()
                            .setChatId(chatId)
                            .setText(text);
                    try {
                        // Call method to send the message
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                        TELEGRAM_ERROR_LOG.error("send error message:{}", JSON.toJSONString(message));
                        continue;
                    }
                    TELEGRAM_LOG.info("send success message:{}", JSON.toJSONString(message));
                }
            }

            if (update.getMessage().hasText()) {
                TELEGRAM_LOG.info("receive success message:{}", JSON.toJSONString(update.getMessage().getText()));
            }
        }
    }

    @Override
    public String getBotUsername() {
        return Constant.BOT_USER_NAME;
    }
}
