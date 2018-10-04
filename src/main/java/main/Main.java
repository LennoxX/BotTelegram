package main;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import controllers.BotController;

public class Main {
	public static void main(String[] args) {
		ApiContextInitializer.init();
		TelegramBotsApi botsApi = new TelegramBotsApi();
		BotController bot = new BotController();
		bot.init();
		try {
			botsApi.registerBot(bot);
			System.out.println("Bot iniciado");
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

}
