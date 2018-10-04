package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Usuario;
import utils.Conexao;

public class UsuariosDAO {
	private Usuario usuario;
	PreparedStatement pst;

	public String buscarUsuario() {
		try {
			pst = Conexao.getConection().prepareStatement("Select * from usuarios");
			ResultSet rs = pst.executeQuery();
			rs.next();
			return (rs.getString("nome"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Nada";
	}

	public boolean cadastrar(Usuario usuario2) {
		try {
			pst = Conexao.getConection().prepareStatement("SELECT * FROM USUARIOS WHERE id_telegram = ?");
			pst.setString(1, usuario2.getIdTelegram());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return atualizarUsuario(usuario2);
			} else {
				pst = Conexao.getConection().prepareStatement("INSERT INTO public.usuarios(id_telegram, primeiro_nome, ultimo_nome, email) VALUES (?, ?, ?, ?)");
				pst.setString(1, usuario2.getIdTelegram());
				pst.setString(2, usuario2.getPrimeiroNome());
				pst.setString(3, usuario2.getUltimoNome());
				pst.execute();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean atualizarUsuario(Usuario usuario2) {
		try {
			pst = Conexao.getConection().prepareStatement(
					"UPDATE usuarios SET primeiro_nome=?, ultimo_nome=?, email=? WHERE id_telegram=?");
			pst.setString(1, usuario2.getPrimeiroNome());
			pst.setString(2, usuario2.getUltimoNome());
			pst.setString(3, usuario2.getEmail());
			pst.setString(4, usuario2.getIdTelegram());
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

}
