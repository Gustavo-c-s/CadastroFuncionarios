package views;

import java.sql.Date;

public class Cliente extends Pessoas {
	private String telefone;
	
	public Cliente(String nome, String cpf, String endereco, String dtNascimento, String telefone) {
		super(nome, cpf, endereco, dtNascimento);
		this.telefone = telefone;
	}

	public Cliente() {
		super();
		// TODO Stub de construtor gerado automaticamente
	}

	public Cliente(String nome, String cpf, String endereco, String dtNascimento) {
		super(nome, cpf, endereco, dtNascimento);
		// TODO Stub de construtor gerado automaticamente
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
}
