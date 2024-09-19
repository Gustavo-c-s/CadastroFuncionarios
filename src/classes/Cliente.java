package classes;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import classaDAO.ClienteDao;
import classaDAO.CreateDao;
import util.Ler;
import views.ConexaoBanco;

public class Cliente extends Pessoas {
	private String telefone;
	
	public Cliente(String nome, String cpf, String endereco, LocalDate dtNascimento, String telefone) {
		super(nome, cpf, endereco, dtNascimento);
		this.telefone = telefone;
	}

	public Cliente() {
		super();
		// TODO Stub de construtor gerado automaticamente
	}

	public Cliente(String nome, String cpf, String endereco, LocalDate dtNascimento) {
		super(nome, cpf, endereco, dtNascimento);
		// TODO Stub de construtor gerado automaticamente
	}
	
	public void addCliente(ConexaoBanco con) {
		List<Cliente>cliente = new ArrayList<Cliente>();
		CreateDao creDao =new CreateDao();
		ClienteDao cliDao = new ClienteDao();
		creDao.verificaOuCriaTabela("cliente",con);
		String res;
		do {
			String nome=Ler.lerString("Digete o nome do cliente: ");
						
			String cpf=Ler.lerString("Digete o CPF do cliente: ");
			
			String end = Ler.lerString("Digite o Endereço: ");
			
			LocalDate data = Ler.validarData("Digite data de nascimento: 'dd/MM/yyyy' ", false);
			
			String tel = Ler.lerString("Informe o telefone: ");

			Cliente novoCliente = new Cliente(nome,cpf,end,data,tel);
			cliDao.insertDados(novoCliente,con);
			cliente.add(novoCliente);
		
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
		}while(res.equals("1"));	
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Cliente [telefone=" + telefone + ", nome=" + nome + ", cpf=" + cpf + ", endereco=" + endereco
				+ ", dtNascimento=" + dtNascimento + "]";
	}
	
}
