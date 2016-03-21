package simulador.util;

import java.io.PrintStream;

public class TamanhoSubString {

	public static void posicaoString(String s) {
		PrintStream p = new PrintStream(System.out);
		int i = 0;

		p.println("Posição : caracter\n");

		for (char c : s.toCharArray()) {
			p.println(i + "\t: " + c);
			i++;
		}
		
		p.println(s.length());

	}

	public static void main(String[] args) {

		String s = "201000000000000000000000000000000000000000156415112702010000000000000938";
		
//		String s = "6042624000002414=19105201790000000000";

		posicaoString(s);

		String result = s.substring(s.length() - 13, s.length() - 10);
		System.out.println("result = " + result);
	}
}
