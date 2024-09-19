package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Ler {
	public static void escreva(String msg) {
		System.out.println(msg);
	}
	public static int lerInt(String msg) {
		Scanner input = new Scanner(System.in);
		System.out.println(msg);
		return input.nextInt();
	}
	public static String lerString(String msg) {
		Scanner input = new Scanner(System.in);
		System.out.println(msg);
		return input.nextLine();
		
	}
	public static double lerDouble(String msg) {
		Scanner input = new Scanner(System.in);
		System.out.println(msg);
		return input.nextDouble();
	}	
	public static LocalDate validarData(String msg,boolean force) {
		DateTimeFormatter dtf =DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataConvertida = null;
		String sData;
		boolean dataValida = false;
	
		do {
			sData=lerString(msg);
			
			if(!sData.isEmpty()||!sData.isBlank()||force)
				try{
					dataConvertida = LocalDate.parse(sData,dtf);
					dataValida = true;
					return dataConvertida;
				}catch(Exception e) {
					System.err.println("Data invalida");
				}
		}while(!dataValida);
		return null;
	}
	public static int validarInt(String msg) {
		int num = 0;
		boolean validado= true;
		
		do {
			try {
				String s = lerString(msg);
				num = Integer.parseInt(s);
				validado = true;
				
			}catch (Exception e) {
				System.err.println("Informe um numeri valido - "+e.getMessage());
			}
					
		}while(!validado);
		return num;
	}
	public static double validaDouble(String msg) {
		double num = 0;
		boolean validado= true;
		
		do {
			try {
				String s = lerString(msg);
				num = Double.parseDouble(s);
				validado = true;
				
			}catch (Exception e) {
				System.err.println("Informe um numeri valido - "+e.getMessage());
			}
					
		}while(!validado);
		return num;
	}
	public static String verificarString(String msg) {
		
		String string;
		boolean stringValida = false;
		do {
			string =lerString(msg);
			if(!string.isEmpty()||!string.isBlank()) {
				stringValida =true;
				return string;
			}
			System.out.println("Não pode ser vazio");
		}while(!stringValida);
		return null;
	}

	//public static Object 
	
	public static void errorDividir0() {
		int a = 10;
		int b =0;
		int c= 0;
		
		try {
			c=a/b;
			System.out.println("Resultado: "+ c);
		} catch (ArithmeticException e) {
			System.err.println("Nao foi possivel dividir por zero");
		}
	}
	public void nulo() {
		String fraseEntrada = null;
		String fraseSainda = null;
		try {
			fraseSainda = fraseEntrada.toLowerCase();
			System.out.println(fraseSainda);
		} catch (Exception e) {
			System.err.println("Erro"+e.getClass());
		}
	}

}
//	public static String proCpf(String cpf,List<Funcionario > funcionarios) {
//		for(String n = cpf;!n.equals(((Funcionario) funcionarios).getCpf());n=Ler.lerString("")){
//			System.out.println("erro");array.contains(String);
//		}
//	}for(int i 0; i<lista.size();i++){
//		if(lista.get(i) instanceof array.contains())
//	}
//	public int compareYo(pessoa 0) {
//		if(this.idade<o.idade) {
//			return -1;
//		
//		}else if (this.idade>o.idade) {
//			return 1;
//		}else {
//			return 0;
//		}
//	}

//for(string s :classePessoa.keySet) {
//	print(s)
//}

