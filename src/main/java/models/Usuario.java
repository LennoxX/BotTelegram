package models;

public class Usuario {

	private int id;
	private String idTelegram;
	private String primeiroNome;
	private String ultimoNome;
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdTelegram() {
		return idTelegram;
	}

	public void setIdTelegram(String idTelegram) {
		this.idTelegram = idTelegram;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public String getUltimoNome() {
		return ultimoNome;
	}

	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", idTelegram=" + idTelegram + ", primeiroNome=" + primeiroNome + ", ultimoNome="
				+ ultimoNome + ", email=" + email + "]";
	}



}
