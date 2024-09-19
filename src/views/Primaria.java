package views;
import java.util.Date;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import classaDAO.ClienteDao;
import classaDAO.CreateDao;
import classaDAO.FuncionarioDao;
import classes.Cliente;
import classes.Funcionario;
import util.Ler;


			
public class Primaria {	
 	public static void main(String[] args) {
 		menu();
 	}
 	public static void menu() {
 		Date relogio = new Date();
 		SimpleDateFormat formatoData = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy, HH:mm", new Locale("pt", "BR"));
 		String opcao="";
		String dataFor = formatoData.format(relogio);
		do {
		
			System.err.println(dataFor);
			System.out.println("""
					***************************
					|     Bem Vindo ao DpPs   |
					|                         |
					|1 - Cadastra Funcionario.|
					|2 - Editar Funcionaro.   |
					|3 - Registra Ponto.      |
					|4 - Folha de Pagamento.  |
					|5 - Lista de Funcionaio. |
					|6 - Cadastra cliente.    |
					|7 - Lista de clientes.   |
					|8 - Editar Cliente.      |
					|0 - Sair                 |
					|                         |
					***************************
					""");
			 opcao = Ler.lerString("Escolha uma opção: ");
			 escolherOpcao(opcao);			
		}while(!opcao.equals("0"));
		  
	}
	private static void contatorColuna(ConexaoBanco con) {
		con.sqlPronto("funcionario");
		
	}
	public static void cadastraFuncionario(ConexaoBanco con){
		Funcionario fun = new Funcionario();
		fun.addFuncionario(con);
	
//		creDao.verificaOuCriaTabela("funcionario",con);
		//do {
			
			
			
//			String nome=Ler.lerString("Digete o nome do funcionario: ");
//						
//			String cpf=Ler.lerString("Digete o CPF do funcionario: ");
//			
//			LocalDate data= null;
//	        boolean dataValida = false;
//	        while(!dataValida) {
//				
//				String dt = Ler.lerString("Informe a data de nascimento: 'yyyy-MM-dd' ");
//				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//				data=LocalDate.parse(dt,formatter);
//				dataValida = true;
//			//ate aqui	
//	        }
//			String endereco = Ler.lerString("Informe o endereco: ");
//			String telefone = Ler.lerString("Informe o telefone");
//			double salario = Ler.validaDouble("Digete o salario do funcionario: ");
//
//			Funcionario novoFuncionario = new Funcionario(nome,cpf,endereco,data,salario,telefone);
//			
//			funcionarios.add(novoFuncionario);
//			funDao.insertDados(novoFuncionario,con);
//			while(true) {
//				res = Ler.lerString("Deseja continuar cadastrando? 1 - sim / 2 - retonar");
//				if (res.equals("2")) {
//					return;
//				}else if (res.equals("1")){
//					break;
//				}else {
//					System.out.println("OPÇÃO INVÁLIDA. TENTE NOVAMENTE.");
//				}
//			}
		//}while(res.equals("1"));	
	}	
	public static void editarFuncionario(ConexaoBanco con,CreateDao creDao) {
		String cpf,opcao1="";
		String res;
		
		do {
			cpf = Ler.lerString("Digite o CPF do funcionario: ");
			
			if(creDao.verificarCPF(cpf,"funcionario",con)) {
						
				res = Ler.lerString("""				
						Oque deseja editar:
					|[1] Nome     [2] CPF    |
					|[3]Endereço  [4]Telefone|
					|________________________| """);
				switch (res) {
				case "1": 
					String nome = Ler.lerString("Digite o novo nome:");
					creDao.alteraTabela("funcionario","nome",nome,cpf,con);
					creDao.verificarCPF(cpf,"funcionario",con);
					break;
				case "2":
					String cpfn = Ler.lerString("Digite o novo CPF:");
					creDao.alteraTabela("funcionario","cpf",cpfn,cpf,con);
					creDao.verificarCPF(cpf,"funcionario",con);
					break;
				case "3":
					double valor = Ler.lerDouble("Digite o novo Salario:");
					creDao.alteraSalario(valor,cpf,con);
					creDao.verificarCPF(cpf,"funcionario",con);
					break;
				case "4":
					String end =Ler.lerString("Digite o novo endereço: ");
					creDao.alteraTabela("funcionario","endereco", end, cpf,con);
					creDao.verificarCPF(cpf,"funcionario",con);
					break;
				case "5":
					String tl = Ler.lerString("Digite o novo telefone: ");
					creDao.alteraTabela("funcionario","telefone", tl, cpf,con);
					creDao.verificarCPF(cpf,"funcionario",con);
				default:
					throw new IllegalArgumentException("Unexpected value: " + res);
				}
			}else {
				System.out.println("Cpf não encontrado.");
			}
			while(true) {				
				opcao1 = Ler.lerString("Deseja edita outro funcionario? 1 - sim / 2 - retonar");					
				if (opcao1.equals("2") ) {
					return;
				}else if (opcao1.equals("1")){
					break;
				}else {
					System.out.println("OPÇÃO INVÁLIDA. TENTE NOVAMENTE.");
				}
			}
			
		}while(opcao1.equals("1"));				
//				if (res.equals("1")) {
//					String nome = Ler.lerString("Digite o novo nome:");
//					//funcionario.setNome(nome);
//					con.alteraTabela("nome",nome,cpf);
//					con.verificarCPF(cpf);
//					System.out.println("Funcionario editado com sucesso!!\n\nfuncionario:\nNome: "+funcionario.getNome()+"\nCPF: "+ funcionario.getCpf()+"\nSalario: "+funcionario.getSalario());
//				}else if(res.equals("2")) {
//					String cpfn = Ler.lerString("Digite o novo CPF:");
//					con.alteraTabela("cpf",cpfn,cpf);
//					con.verificarCPF(cpf);
//
//				}else if(res.equals("3")) {
//					double valor = Ler.lerDouble("Digite o novo Salario:");
//					String salario = Double.toString(valor);
//					con.alteraSalario(valor,cpf);
//					con.verificarCPF(cpf);
				
	}	
	public static Funcionario procuraCpf(String cpf, List<Funcionario > funcionarios) {
		for(Funcionario f : funcionarios){
			if(f.getCpf().equals(cpf)) {
				return f;
			}
		}
		return null;
	}
//	public static void registraPonto(List<Funcionario>funcionarios) {		 
//		DateTimeFormatter hora = DateTimeFormatter.ofPattern("HH:mm"); 
//		String cpfhr;
//		int res =0 ;		
//		
//		do {
//            try {
//            	cpfhr = Ler.lerString("Digite o CPF do funcionaro: ");
//            	Funcionario funcionario = procuraCpf(cpfhr,funcionarios);		
//            	if(funcionario.getCpf().equals(cpfhr)) {
//	                String hrE =Ler.lerString("Digite a hora de entrada no formato HH:mm");        
//	                String hrS =Ler.lerString("Digite a hora de saida no formato HH:mm");           				
//	                LocalTime horae = LocalTime.parse(hrE, hora);
//	                LocalTime horas = LocalTime.parse(hrS, hora);	                
//	                funcionario.setHrEntrada(horae);
//	                funcionario.setHrSaida(horas);
//	                System.out.println("Horário entrada: " + horae);
//	                System.out.println("Horário saida: " + horas);
//	            	
//	    			res = Ler.lerInt("Deseja registra outro ponto?  1 - sim / 2 - retonar");
//	    			if (res == 2 ) {
//	    				return;
//	    			}else if (res != 1){
//	    				System.out.println("OPÇÃO INVÁLIDA. TENTE NOVAMENTE.");	    				
//	    			}	
//            	}
//            }catch (DateTimeParseException e) {
//                System.out.println("Formato de horário inválido. Tente novamente.");
//            }                   		            	
//		}while(res == 1);
//	}
	public static void folhaPagamento(List<Funcionario> funcionarios,ConexaoBanco con) {
		double totalFolhaPagamento = 0.0;
		
		String cpf = Ler.lerString("Digite o cpf do funcionario: ");
		System.out.println("Folha de Pagamento: ");
		System.out.println("-----------------------");
		
//		for (Funcionario f : funcionarios) {
//			if(f !=null ){
				double salarioBruto = con.pegaValor(cpf);
				double descontoINSS=calcularINSS(salarioBruto);
				double salarioAposINSS = salarioBruto - descontoINSS;
				double descontoIRRF = calcularIRRF(salarioAposINSS);
				double salarioLiquido = salarioAposINSS - descontoIRRF;
				System.out.println("**************************");
				System.out.printf("| Salário Bruto: R$ %.2f \n| INSS: R$ %.2f \n| IRRF: R$ %.2f \n| Salário Líquido: R$ %.2f%n",
								salarioBruto, descontoINSS,descontoIRRF,salarioLiquido);
				System.out.println("**************************");
				totalFolhaPagamento += salarioLiquido;
				System.out.printf("Folha de pagamento total: R$ %.2f%n",totalFolhaPagamento);
//				}funcionarioCadastrados = true;
//		}		
	}
	public static double calcularINSS(double salario) {
		if (salario<= 1320.00) {
			return salario*0.075;
		}else if(salario <= 2571.29) {
			return salario * 0.09;
		}else if (salario <= 3856.94) {
			return salario*0.12;
		}else if(salario <= 7507.49) {
			return salario * 0.14;
		}else {
			return 7507.49 * 0.14;
		}
	}
	public static double calcularIRRF(double salarioAposINSS) {
		if(salarioAposINSS <= 1903.98) {
			return 0;
		}else if(salarioAposINSS <= 2826.65){
			return salarioAposINSS * 0.075 - 142.80;
		}else  if(salarioAposINSS <=3751.05) {
			return salarioAposINSS * 0.15- 354.80;
		}else if(salarioAposINSS <= 4664.68) {
			return salarioAposINSS * 0.225 - 636.13;			
		}else {
			return salarioAposINSS * 0.275-869.36;
		}
	}	
	public static void listaFuncionario(ConexaoBanco con,CreateDao creDao) {		
		System.out.println("Lista de Funcionários:");
		creDao.consultaTabela("funcionario",con);
		
//		    boolean encontrado = false;
//		    
//		    for (Funcionario f : funcionarios) {
//		        
//		    	if (f != null) {
//		    		System.err.println("*******************");	        	
//		            System.out.println(f);	
//		            System.err.println("*******************");	
//		            encontrado = true;		            
//		        }
//		    }
//		    if (!encontrado) {
//		        System.out.println("Nenhum funcionário cadastrado.");
//		    }
	}
	public static void cadastraCliente(ConexaoBanco con){
		Cliente cliente = new Cliente();
		cliente.addCliente(con);	
	}
	public static void listaCliente(ConexaoBanco con,CreateDao creDao) {
		System.out.println("Lista de Cliente: ");
		creDao.consultaTabela("cliente",con);
	}
	public static void editarCliente(ConexaoBanco con,CreateDao creDao) {
		String cpf,opcao1="";
		String res;
		
		do {
			cpf = Ler.lerString("Digite o CPF do cliente ");
			
			if(creDao.verificarCPF(cpf,"cliente",con)) {
						
				res = Ler.lerString("""				
						Oque deseja editar:
					|[1] Nome     [2] CPF    |
					|[3]Endereço  [4]Telefone|
					|________________________| """);
				switch (res) {
				case "1": 
					String nome = Ler.lerString("Digite o novo nome:");
					creDao.alteraTabela("cliente","nome",nome,cpf,con);
					creDao.verificarCPF(cpf,"cliente",con);
					break;
				case "2":
					String cpfn = Ler.lerString("Digite o novo CPF:");
					creDao.alteraTabela("cliente","cpf",cpfn,cpf,con);
					creDao.verificarCPF(cpf,"cliente",con);
					break;
				case "3":
					String end =Ler.lerString("Digite o novo endereço: ");
					creDao.alteraTabela("cliente","endereco", end, cpf,con);
					creDao.verificarCPF(cpf,"cliente",con);
					break;
				case "4":
					String tl = Ler.lerString("Digite o novo telefone: ");
					creDao.alteraTabela("cliente","telefone", tl, cpf,con);
					creDao.verificarCPF(cpf,"cliente",con);
				default:
					throw new IllegalArgumentException("Unexpected value: " + res);
				}
			}else {
				System.out.println("Cpf não encontrado.");
			}
			while(true) {				
				opcao1 = Ler.lerString("Deseja edita outro funcionario? 1 - sim / 2 - retonar");					
				if (opcao1.equals("2") ) {
					return;
				}else if (opcao1.equals("1")){
					break;
				}else {
					System.out.println("OPÇÃO INVÁLIDA. TENTE NOVAMENTE.");
				}
			}
		}while(opcao1.equals("1"));	
	}
	public static void escolherOpcao(String opcao) {
		
		List<Funcionario>funcionario = new ArrayList<Funcionario>();
		ConexaoBanco con = new ConexaoBanco();
		CreateDao creDao = new CreateDao();
		
				
			switch (opcao) {
				 case "1":cadastraFuncionario(con); break;
				 case "2":editarFuncionario(con,creDao); break;
				 case "3" ://registraPonto(funcionarios); break;
				 case "4": folhaPagamento(funcionario,con); break;
				 case "5": listaFuncionario(con,creDao); break;
				 case "6": cadastraCliente(con); break;
				 case "7": listaCliente(con,creDao);
					 break;
				 case "8":editarCliente(con,creDao);break;
				 case "0":
				 System.out.println("ENCERRANDO O PROGRAMA...");
				 default:
				     System.out.println("OPÇÃO INVÁLIDA. TENTE NOVAMENTE.");
			}
		
	}
	
}	
	//	public static void cadastraCliente(List<Cliente>cliente,ConexaoBanco con) {
//		String res="";
//		con.verificaOuCriaTabela("cliente");
//		do {
//			String nome =Ler.lerString("Informe o nome: ");
//			String cpf =Ler.lerString("Informe o cpf: ");
//			String end = Ler.lerString("Informe o endereço: ");
//			String tl =Ler.lerString("Informe um telefone: ");
//			String dt = Ler.lerString("Informe a data de nascimento: ");
//			Cliente novoCliente = new Cliente(nome,cpf,end,dt,tl);
//			cliente.add(novoCliente);
//			con.insertDados(novoCliente);
//			while(true) {
//				res = Ler.lerString("Deseja continuar cadastrando? 1 - sim / 2 - retonar");
//				if (res.equals("2")) {
//					return;
//				}else if (res.equals("1")){
//					break;
//				}else {
//					System.out.println("OPÇÃO INVÁLIDA. TENTE NOVAMENTE.");
//				}
//			}
//		}while(res.equals("1"));
//	}


