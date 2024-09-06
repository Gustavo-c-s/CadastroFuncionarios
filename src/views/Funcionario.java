package views;

import java.time.LocalTime;

public class Funcionario {
	private int codigo;
	private String nome;
	private String cpf;
	private double salario;
	private LocalTime hrEntrada;
	private LocalTime hrSaida;
	private static int cd;
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
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

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public LocalTime getHrEntrada() {
		return hrEntrada;
	}

	public void setHrEntrada(LocalTime horae) {
		this.hrEntrada = horae;
	}

	public LocalTime getHrSaida() {
		return hrSaida;
	}

	public void setHrSaida(LocalTime horas) {
		this.hrSaida = horas;
	}

	public Funcionario(String nome,String cpf, double salario) {
		cd++;
		this.codigo=cd;
		this.nome =nome;
		this.cpf=cpf;
		this.salario=salario;
		this.hrEntrada=null;
		this.hrSaida=null;
	}

	
}

