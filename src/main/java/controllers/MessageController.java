package controllers;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MessageController extends TelegramLongPollingBot{

	public String getBotUsername() {
		return "TESTEEEEEjavaBot";
	}

	@Override
	public String getBotToken() {
		return "613177691:AAHI76ZxhnrT0pkia9kWQAEe2lAEWL04PMQ";
	}
		
	public void replaceMessageWithText(long chatId, long messageId, String text) {
		EditMessageText newMessage = new EditMessageText().setChatId(chatId).setMessageId(Math.toIntExact(messageId))
				.setText(text);
		try {
			execute(newMessage);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	public void deleteMessage(long chatId, long messageId) {
		DeleteMessage messageToDelete = new DeleteMessage().setChatId(chatId).setMessageId(Math.toIntExact(messageId));
		try {
			execute(messageToDelete);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(long chatId, String text) {
		SendMessage newMessage = new SendMessage().setChatId(chatId).setText(text);
		try {
			execute(newMessage);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	public void replaceMessage(long chatId, long messageId, SendMessage message) {
		EditMessageText newMessage = new EditMessageText().setChatId(chatId).setMessageId(Math.toIntExact(messageId))
				.setText(message.getText()).setReplyMarkup((InlineKeyboardMarkup) message.getReplyMarkup());
		try {
			execute(newMessage);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpdateReceived(Update update) {
		// TODO Auto-generated method stub
		
	}
}
