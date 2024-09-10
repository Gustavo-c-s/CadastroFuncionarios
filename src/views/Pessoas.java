package views;

import java.sql.Date;

public abstract class Pessoas {
	protected String nome;
	protected String cpf;
	protected String endereco;
	protected String dtNascimento;
	public Pessoas(String nome, String cpf, String endereco, String dtNascimento) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.dtNascimento = dtNascimento;
	}
	public Pessoas() {
		super();
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getDtNascimento() {
		return dtNascimento;
	}
	public void setDtNascimento(String dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	
	
	
}
