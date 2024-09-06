package views;

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
