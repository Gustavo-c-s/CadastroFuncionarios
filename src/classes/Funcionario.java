package classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import classaDAO.CreateDao;
import classaDAO.FuncionarioDao;
import util.Ler;
import views.ConexaoBanco;

public class Funcionario extends Pessoas {

	private double salario;
	private String telefone;
	public Funcionario(String nome, String cpf, String endereco, LocalDate dtNascimento, double salario,String telefone) {
		super(nome, cpf, endereco, dtNascimento);
		this.salario = salario;
		this.telefone = telefone;
	}

	public Funcionario() {
		super();
		// TODO Stub de construtor gerado automaticamente
	}

	public Funcionario(String nome, String cpf, String endereco, LocalDate dtNascimento) {
		super(nome, cpf, endereco, dtNascimento);
		// TODO Stub de construtor gerado automaticamente
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void addFuncionario(ConexaoBanco con) {
		List<Funcionario>funcionario = new ArrayList<Funcionario>();
		FuncionarioDao funDao = new FuncionarioDao();
		CreateDao creDao = new CreateDao();
		creDao.verificaOuCriaTabela("funcionario",con);
		String res ="";
		
		String nome=Ler.verificarString("Digete o nome do funcionario: ");
		String cpf=Ler.verificarString("Digete o CPF do funcionario: ");
		LocalDate data= Ler.validarData("Digite data de nascimento: 'dd/MM/yyyy' ", false);
		String endereco = Ler.verificarString("Informe o endereco: ");
		String telefone = Ler.verificarString("Informe o telefone");
		double salario = Ler.validaDouble("Digete o salario do funcionario: ");

		Funcionario novoFuncionario = new Funcionario(nome,cpf,endereco,data,salario,telefone);
		funcionario.add(novoFuncionario);
		funDao.insertDados(novoFuncionario,con);
		while(true) {
			res = Ler.lerString("Deseja continuar cadastrando? 1 - sim / 2 - retonar");
			if (res.equals("2")) {
				return;
			}else if (res.equals("1")){
				break;
			}else {
				System.out.println("OPÇÃO INVÁLIDA. TENTE NOVAMENTE.");
			}
		}
		
	}

	@Override
	public String toString() {
		return "Funcionario [salario=" + salario + ", telefone=" + telefone + ", nome=" + nome + ", cpf=" + cpf
				+ ", endereco=" + endereco + ", dtNascimento=" + dtNascimento + "]";
	}
	
	
}

