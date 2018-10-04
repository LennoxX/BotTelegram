package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import models.MenuItem;
import models.Usuario;
import utils.InlineKeyboardBuilder;
import utils.MenuManager;

public class BotController extends TelegramLongPollingBot {

	private MenuManager menuPrincipal = new MenuManager();
	private MenuManager menuSecundario = new MenuManager();
	private CadastroController cadController = new CadastroController();
	private MessageController messageController = new MessageController();

	private SendMessage message;
	private long chatId;
	private String callData;
	private long messageId;
	private InlineKeyboardBuilder builder;
	private Usuario usuario;
	public static final int HOME = 0, CADASTRO = 1;
	public int menuOpcao = 0;
	public static long SENT_MESSAGE;

	public void init() {

		menuPrincipal.setColumnsCount(2);
		menuPrincipal.addMenuItem(new MenuItem("Horario", "horario"));
		menuPrincipal.addMenuItem(new MenuItem("Olá", "ola"));
		menuPrincipal.addMenuItem(new MenuItem("Cadastro", "cadastro"));
		menuPrincipal.init();
		initMenuSecundario();
	}

	public void initMenuSecundario() {
		menuSecundario.setColumnsCount(2);
		menuPrincipal.init();
	}

	@Override
	public void onUpdateReceived(Update update) {

		if (update.hasMessage() && update.getMessage().hasText()) {
			if (menuOpcao == CADASTRO) {
				usuario = new Usuario();
				usuario.setIdTelegram(update.getMessage().getFrom().getId().toString());
				usuario.setPrimeiroNome(update.getMessage().getFrom().getFirstName());
				usuario.setUltimoNome(update.getMessage().getFrom().getLastName());
				usuario.setEmail(update.getMessage().getText());
				cadController.cadastrarUsuario(usuario, chatId, messageId);
				menuOpcao = HOME;
			}

			if (update.getMessage().getText().equals("/menu@" + getBotUsername())
					|| update.getMessage().getText().equals("/menu")) {
				chatId = update.getMessage().getChatId();
				builder = menuPrincipal.createMenuForPage(0, true);
				builder.setChatId(chatId).setText("Selecione uma Opção: ");
				message = builder.build();
				try {
					execute(message);
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			} else {

			}
		} else if (update.hasCallbackQuery()) {
			chatId = update.getCallbackQuery().getMessage().getChatId();
			callData = update.getCallbackQuery().getData();
			messageId = update.getCallbackQuery().getMessage().getMessageId();
			if (callData.equals(MenuManager.CANCEL_ACTION)) {
				messageController.deleteMessage(chatId, messageId);
			} else if (callData.startsWith(MenuManager.PREV_ACTION) || callData.startsWith(MenuManager.NEXT_ACTION)) {
				String pageNum = "0";
				if (callData.startsWith(MenuManager.PREV_ACTION)) {
					pageNum = callData.replace(MenuManager.PREV_ACTION + ":", "");
				} else {
					pageNum = callData.replace(MenuManager.NEXT_ACTION + ":", "");
				}
				builder = menuPrincipal.createMenuForPage(Integer.parseInt(pageNum), true);
				builder.setChatId(chatId).setText("Choose action:");
				message = builder.build();
				messageController.replaceMessage(chatId, messageId, message);
			} else if (callData.equals("horario")) {
				chatId = update.getCallbackQuery().getMessage().getChatId();
				callData = update.getCallbackQuery().getData();
				messageId = update.getCallbackQuery().getMessage().getMessageId();
				messageController.replaceMessageWithText(chatId, messageId, getSystemTime());
				System.out
						.println(update.getCallbackQuery().getFrom().getFirstName() + " selecionou a opção 'Horario'");
			} else if (callData.equals("ola")) {
				chatId = update.getCallbackQuery().getMessage().getChatId();
				callData = update.getCallbackQuery().getData();
				messageId = update.getCallbackQuery().getMessage().getMessageId();
				messageController.replaceMessageWithText(chatId, messageId,
						"Olá, " + update.getCallbackQuery().getFrom().getFirstName());
				System.out.println(update.getCallbackQuery().getFrom().getFirstName() + " selecionou a opção 'Olá'");
			} else if (callData.equals("cadastro")) {
				chatId = update.getCallbackQuery().getMessage().getChatId();
				realizaCadastro(chatId, messageId, update);
			}
		}
	}

	public String getSystemTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		return (formatter.format(date));
	}

	public void realizaCadastro(long chatId, long messageId, Update update) {
		builder = menuSecundario.createMenuForPage(0, true);
		builder.setChatId(chatId).setText("Digite seu E-mail: ");
		message = builder.build();
		messageController.replaceMessage(chatId, messageId, message);
		SENT_MESSAGE = messageId;
		menuOpcao = CADASTRO;
	}

	
	//////////////////////////////////MÉTODOS DA INTERFACE//////////////////////////////
	public String getBotUsername() {
		return "TESTEEEEEjavaBot";
	}

	@Override
	public String getBotToken() {
		return "613177691:AAHI76ZxhnrT0pkia9kWQAEe2lAEWL04PMQ";
	}

}
