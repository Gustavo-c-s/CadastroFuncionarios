package views;

import java.util.List;
import java.util.Scanner;

public class Ler {
	
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

