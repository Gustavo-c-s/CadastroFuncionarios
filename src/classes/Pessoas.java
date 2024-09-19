package classes;

import java.sql.Date;
import java.time.LocalDate;

public abstract class Pessoas {
	protected String nome;
	protected String cpf;
	protected String endereco;
	protected LocalDate dtNascimento;
	
public Pessoas(String nome, String cpf, String endereco, LocalDate dtNascimento) {
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
	public LocalDate getDtNascimento() {
		return dtNascimento;
	}
	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	
	
	
}
