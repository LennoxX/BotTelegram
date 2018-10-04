package controllers;

import dao.UsuariosDAO;
import models.Usuario;

public class CadastroController {
	UsuariosDAO dao;
	BotController botController;
	MessageController messageController = new MessageController();
	boolean status;

	public void cadastrarUsuario(Usuario usuario, long chatId, long messageId) {
		botController = new BotController();
		System.out.println(usuario);
		dao = new UsuariosDAO();
		status = dao.cadastrar(usuario);
		if (status) {
			messageController.deleteMessage(chatId, messageId);
			messageController.sendMessage(chatId, usuario.getPrimeiroNome() + ", você foi cadastrado com sucesso!");
		} else {
			messageController.deleteMessage(chatId, messageId);
			messageController.sendMessage(chatId, usuario.getPrimeiroNome() + ", ocorreu um erro durante o cadastro!");
		}
	}

}
