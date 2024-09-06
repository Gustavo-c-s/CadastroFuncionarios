package views;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
		
		
public class Primaria {
	
 	public static void main(String[] args) {
 		menu();
 	}
 	public static void menu() {
 		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		Scanner scanner = new Scanner(System.in);
		String opcao="";
		Date relogio = new Date();
		
		while(opcao != "6") {
			System.err.println(relogio);
			 System.out.println("""
					**************************
					     Bem Vindo ao DpPs
					1 - Cadastra Funcionario.
					2 - Editar Funcionaro.
					3 - Registra Ponto.
					4 - Folha de Pagamento.
					5 - Lista de Funcionaio.
					6 - Sair
					
					**************************
					""");
			 System.out.println("Escolha uma opção: ");
			 opcao = scanner.nextLine();
			
			 switch (opcao) {
				 case "1":
					 cadastraFuncionario(funcionarios);
					 break;
				 case "2":
					 editarFuncionario(funcionarios);
					 break;
				 case "3" :
					 registraPonto(funcionarios);
					 break;
				 case "4":
					 folhaPagamento(funcionarios);
					 break;
				 case "5":
					 listaFuncionario(funcionarios);
					 break;
				 case "6":
				 System.out.println("ENCERRANDO O PROGRAMA...");
				     scanner.nextLine();
					 return;
				 default:
				     System.out.println("OPÇÃO INVÁLIDA. TENTE NOVAMENTE.");
				     scanner.nextLine();
			 }		
		}  
	}
	public static void cadastraFuncionario(List<Funcionario>funcionarios){
		int res; 
		Scanner scanner = new Scanner(System.in);
		
		do {
			String nome;
			System.out.println("Digete o nome do funcionario: ");
			nome =scanner.nextLine();
			
			String cpf;
			System.out.println("Digete o CPF do funcionario: ");
			cpf  =scanner.nextLine();
			
			double salario;
			System.out.println("Digete o salario do funcionario: ");
			salario =scanner.nextDouble();
			
			scanner.nextLine();
			Funcionario novoFuncionario = new Funcionario(nome,cpf,salario);
			funcionarios.add(novoFuncionario);
			
			System.out.println("Deseja continuar cadastrando? 1 - sim / 2 - retonar");
			res = scanner.nextInt();
			scanner.nextLine();
			if (res == 2 ) {
				return;
			}else if (res != 1){
				System.out.println("OPÇÃO INVÁLIDA. TENTE NOVAMENTE.");
				
			}		
		}while(res == 1);	
	}	
	public static void editarFuncionario(List<Funcionario>funcionarios) {
		Scanner scanner = new Scanner(System.in);
		String cpf;
		int res,opcao1 = 1;
		
		System.out.println("Digite o CPF do funcionaro para editar");
		cpf = scanner.nextLine();
		Funcionario funcionario = procuraCpf(cpf,funcionarios);
		
			if(funcionario.getCpf().equals(cpf)) {
				
				System.out.println("Informações do funcionario:\nNome: "+funcionario.getNome()+"\nCPF: "+funcionario.getCpf()+"\nSalario: "+funcionario.getSalario());		
				System.out.println("\nOque deseja editar:\n[1] Nome [2] CPF [3] Salario");
				res = scanner.nextInt();
				scanner.nextLine();
				if (res == 1) {
					String nome;
					System.out.println("Digite o novo nome:");
					nome = scanner.nextLine();
					funcionario.setNome(nome);
					System.out.println("Funcionario editado com sucesso!!\n\nfuncionario:\nNome: "+funcionario.getNome()+"\nCPF: "+ funcionario.getCpf()+"\nSalario: "+funcionario.getSalario());
				}else if(res == 2) {
					String cpfn;
					System.out.println("Digite o novo CPF:");
					cpfn = scanner.nextLine();
					funcionario.setCpf(cpfn);
					System.out.println("Funcionario editado com sucesso!!\n\nfuncionario:\nNome: "+funcionario.getNome()+"\nCPF: "+ funcionario.getCpf()+"\nSalario: "+funcionario.getSalario());
				}else if(res == 3) {
					double salario;
					System.out.println("Digite o novo Salario:");
					salario = scanner.nextDouble();
					funcionario.setSalario(salario);
					System.out.println("Funcionario editado com sucesso!!\n\nfuncionario:\nNome: "+funcionario.getNome()+"\nCPF: "+ funcionario.getCpf()+"\nSalario: "+funcionario.getSalario());
				}
				System.out.println("Deseja edita outro funcionario? 1 - sim / 2 - retonar");
				opcao1 = scanner.nextInt();
				scanner.nextLine();
				if (opcao1 == 2 ) {
					return;
				}else if (opcao1 != 1){
					System.out.println("OPÇÃO INVÁLIDA. TENTE NOVAMENTE.");
					;
				}else if(opcao1 ==1) {
					editarFuncionario(funcionarios);
				}
				
			}
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
		 Scanner input = new Scanner(System.in);
	     DateTimeFormatter hora = DateTimeFormatter.ofPattern("HH:mm"); // FORMATO DE HORA: "HH:mm" 
	     String cpfhr;
	     int res =0 ;
		
		
		
		do {
            try {
            	System.out.println("Digite o CPF do funcionaro: ");
            	cpfhr = input.nextLine();
            	Funcionario funcionario = procuraCpf(cpfhr,funcionarios);
		
            	if(funcionario.getCpf().equals(cpfhr)) {
            		System.out.println("Digite a hora de entrada no formato HH:mm");
	                String hrE =input.nextLine();        
	                System.out.println("Digite a hora de saida no formato HH:mm");
	                String hrS =input.nextLine();        
    				// CONVERTER STRING PARA LocalTime
	                LocalTime horae = LocalTime.parse(hrE, hora);
	                LocalTime horas = LocalTime.parse(hrS, hora);
	                
	                funcionario.setHrEntrada(horae);
	                funcionario.setHrSaida(horas);
	                System.out.println("Horário entrada: " + horae);
	                System.out.println("Horário saida: " + horas);
	                System.out.println("Deseja registra outro ponto?  1 - sim / 2 - retonar");
	            	
	    			res = input.nextInt();
	    			input.nextLine();
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
	public static void folhaPagamento(List<Funcionario> funcionarios) {
		double totalFolhaPagamento = 0.0;
		boolean funcionarioCadastrados = false;
		
		System.out.println("Folha de Pagamento: ");
		System.out.println("-----------------------");
		
		for (Funcionario f : funcionarios) {
			if(f !=null ){
				double salarioBruto = f.getSalario();
				double descontoINSS=calcularINSS(salarioBruto);
				double salarioAposINSS = salarioBruto - descontoINSS;
				double descontoIRRF = calcularIRRF(salarioAposINSS);
				double salarioLiquido = salarioAposINSS - descontoIRRF;
				System.out.println("**************************");
				System.out.printf("| Matricula: %s \n| Nome: %s \n| Salário Bruto: R$ %.2f \n| INSS: R$ %.2f \n| IRRF: R$ %.2f \n| Salário Líquido: R$ %.2f%n",
						f.getCodigo(),f.getNome(), salarioBruto, descontoINSS,descontoIRRF,salarioLiquido);
				System.out.println("**************************");
				totalFolhaPagamento += salarioLiquido;
				funcionarioCadastrados = true;
			}
		}
		
		if(!funcionarioCadastrados) {
			System.out.println("Nenhum funcionario cadastrado.");
		}else {
			System.out.printf("Folha de pagamento total: R$ %.2f%n",totalFolhaPagamento);
		}	
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
	public static void listaFuncionario(List<Funcionario>funcionarios) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Lista de Funcionários:");
		    boolean encontrado = false;
		    for (Funcionario f : funcionarios) {
		        if (f != null) {
		        	System.err.println("*******************");
		            System.out.println("Madricula: "+ f.getCodigo()+"\nNome: " + f.getNome() + "\nCPF: " + f.getCpf() + "\nSalário: " + f.getSalario());
		            
		            encontrado = true;
		            
		        }
		    }scanner.nextLine();

		    if (!encontrado) {
		        System.out.println("Nenhum funcionário cadastrado.");
		    }
	}
}
