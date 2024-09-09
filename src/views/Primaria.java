package views;
import java.util.Date;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
			
public class Primaria {	
 	public static void main(String[] args) {
 		menu();
 	}
 	public static void menu() {
 		Date relogio = new Date();
 		SimpleDateFormat formatoData = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy, HH:mm", new Locale("pt", "BR"));
 		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		String opcao="";
		String dataFor = formatoData.format(relogio);
		ConexaoBanco con = new ConexaoBanco();
		while(opcao != "6") {
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
					|6 - Sair                 |
					|                         |
					***************************
					""");
			 opcao = Ler.lerString("Escolha uma opção: ");			
			 switch (opcao) {
				 case "1":
					 cadastraFuncionario(funcionarios,con);
					 break;
				 case "2":
					 editarFuncionario(funcionarios,con);
					 break;
				 case "3" :
					 registraPonto(funcionarios);
					 break;
				 case "4":
					 folhaPagamento(funcionarios,con);
					 break;
				 case "5":
					 listaFuncionario(funcionarios,con);
					 break;
				 case "6":
				 System.out.println("ENCERRANDO O PROGRAMA...");
					 return;
				 default:
				     System.out.println("OPÇÃO INVÁLIDA. TENTE NOVAMENTE.");
			 }		
		}  
	}
	public static void cadastraFuncionario(List<Funcionario>funcionarios,ConexaoBanco con){
		String res; 
		con.verificaOuCriaTabela("funcionario");
		do {
			String nome=Ler.lerString("Digete o nome do funcionario: ");
						
			String cpf=Ler.lerString("Digete o CPF do funcionario: ");
			
			double salario = Ler.lerDouble("Digete o salario do funcionario: ");

			Funcionario novoFuncionario = new Funcionario(nome,cpf,salario);
			
			funcionarios.add(novoFuncionario);
			con.insertDados(novoFuncionario);
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
	public static void editarFuncionario(List<Funcionario>funcionarios,ConexaoBanco con) {
		String cpf,opcao1="";
		String res;
		
		do {
			cpf = Ler.lerString("Digite o CPF do funcionario: ");
			
			if(con.verificarCPF(cpf)) {
						
				res = Ler.lerString("\nOque deseja editar:\n[1] Nome [2] CPF [3] Salario");
				
				if (res.equals("1")) {
					String nome = Ler.lerString("Digite o novo nome:");
					//funcionario.setNome(nome);
					con.alteraTabela("nome",nome,cpf);
					con.verificarCPF(cpf);
//					System.out.println("Funcionario editado com sucesso!!\n\nfuncionario:\nNome: "+funcionario.getNome()+"\nCPF: "+ funcionario.getCpf()+"\nSalario: "+funcionario.getSalario());
				}else if(res.equals("2")) {
					String cpfn = Ler.lerString("Digite o novo CPF:");
					con.alteraTabela("cpf",cpfn,cpf);
					con.verificarCPF(cpf);

				}else if(res.equals("3")) {
					double valor = Ler.lerDouble("Digite o novo Salario:");
//					String salario = Double.toString(valor);
					con.alteraSalario(valor,cpf);
					con.verificarCPF(cpf);
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
			}
		}while(opcao1.equals("1"));
	}	
	public static Funcionario procuraCpf(String cpf, List<Funcionario > funcionarios) {
		for(Funcionario f : funcionarios){
			if(f.getCpf().equals(cpf)) {
				return f;
			}
		}
		return null;
	}
	public static void registraPonto(List<Funcionario>funcionarios) {		 
		DateTimeFormatter hora = DateTimeFormatter.ofPattern("HH:mm"); 
		String cpfhr;
		int res =0 ;		
		
		do {
            try {
            	cpfhr = Ler.lerString("Digite o CPF do funcionaro: ");
            	Funcionario funcionario = procuraCpf(cpfhr,funcionarios);		
            	if(funcionario.getCpf().equals(cpfhr)) {
	                String hrE =Ler.lerString("Digite a hora de entrada no formato HH:mm");        
	                String hrS =Ler.lerString("Digite a hora de saida no formato HH:mm");           				
	                LocalTime horae = LocalTime.parse(hrE, hora);
	                LocalTime horas = LocalTime.parse(hrS, hora);	                
	                funcionario.setHrEntrada(horae);
	                funcionario.setHrSaida(horas);
	                System.out.println("Horário entrada: " + horae);
	                System.out.println("Horário saida: " + horas);
	            	
	    			res = Ler.lerInt("Deseja registra outro ponto?  1 - sim / 2 - retonar");
	    			if (res == 2 ) {
	    				return;
	    			}else if (res != 1){
	    				System.out.println("OPÇÃO INVÁLIDA. TENTE NOVAMENTE.");	    				
	    			}	
            	}
            }catch (DateTimeParseException e) {
                System.out.println("Formato de horário inválido. Tente novamente.");
            }                   		            	
		}while(res == 1);
	}
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
	public static void listaFuncionario(List<Funcionario>funcionarios,ConexaoBanco con) {		
		System.out.println("Lista de Funcionários:");
		con.consultaDados("funcionario");
		
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
}
