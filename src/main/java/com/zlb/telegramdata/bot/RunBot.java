package com.zlb.telegramdata.bot;

import com.zlb.telegramdata.util.HttpUtils;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class RunBot {

    @PostConstruct
    public void runBot() {
        new BotThread().start();

        //http请求获取数据
        //new BotDownloadDataThread().start();

    }


    private class BotThread extends Thread {

        @Override
        public void run() {
            ApiContextInitializer.init();
            TelegramBotsApi botsApi = new TelegramBotsApi();
            try {
                botsApi.registerBot(new MyBot());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }


    private class BotDownloadDataThread extends Thread {

        String url = "https://api.telegram.org/bot615979347:AAGkKyJ5kDFbuXPBfYxRbD9JNODhkP576N4/getUpdates";
        Map<String, String> params = new HashMap<>();
        Map<String, String> header = new HashMap<>();

        @Override
        public void run() {
            while (true) {
                String returnStr = HttpUtils.doGet(url, params, header);
                System.out.println(returnStr);
            }
        }
    }
}
