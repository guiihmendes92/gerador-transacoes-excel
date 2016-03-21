package simulador.excel.cabal;

import java.util.HashMap;

import simulador.abstratas.ExcelPadrao;

public class PlanilhaTransacoesCabal extends ExcelPadrao {

	public PlanilhaTransacoesCabal(String localArquivo) {
		super(localArquivo);
		setPreencheCampo(new PreencheCampoCabal());
		setReversa(new ReversaCabal());
	}

	static {

		colunaBit = new HashMap<Integer, Integer>();

		colunaBit.put(0, 0);
		colunaBit.put(1, 1);
		colunaBit.put(2, 2);

		colunaBit.put(3, 0);
		colunaBit.put(4, 2);
		colunaBit.put(5, 3);
		colunaBit.put(6, 4);
		colunaBit.put(7, 5);
		colunaBit.put(8, 6);
		colunaBit.put(9, 7);
		colunaBit.put(10, 9);
		colunaBit.put(11, 10);
		colunaBit.put(12, 11);
		colunaBit.put(13, 12);
		colunaBit.put(14, 13);
		colunaBit.put(15, 14);
		colunaBit.put(16, 18);
		colunaBit.put(17, 22);
		colunaBit.put(18, 23);
		colunaBit.put(19, 26);
		colunaBit.put(20, 32);
		colunaBit.put(21, 33);
		colunaBit.put(22, 35);
		colunaBit.put(23, 37);
		colunaBit.put(24, 38);
		colunaBit.put(25, 39);
		colunaBit.put(26, 41);
		colunaBit.put(27, 42);
		colunaBit.put(28, 43);
		colunaBit.put(29, 45);

		colunaBit.put(30, 48);
		colunaBit.put(31, 48);
		colunaBit.put(32, 48);
		colunaBit.put(33, 48);
		colunaBit.put(34, 48);
		colunaBit.put(35, 48);
		colunaBit.put(36, 48);
		colunaBit.put(37, 48);
		colunaBit.put(38, 48);
		colunaBit.put(39, 48);
		colunaBit.put(40, 48);
		colunaBit.put(41, 48);
		colunaBit.put(42, 48);
		colunaBit.put(43, 48);
		colunaBit.put(44, 48);
		colunaBit.put(45, 48);
		colunaBit.put(46, 48);
		colunaBit.put(47, 48);
		colunaBit.put(48, 48);
		colunaBit.put(49, 48);
		colunaBit.put(50, 48);
		colunaBit.put(51, 48);
		colunaBit.put(52, 48);
		colunaBit.put(53, 48);
		colunaBit.put(54, 48);
		colunaBit.put(55, 48);
		colunaBit.put(56, 48);

		colunaBit.put(57, 49);
		colunaBit.put(58, 50);
		colunaBit.put(59, 51);
		colunaBit.put(60, 52);
		colunaBit.put(61, 55);
		colunaBit.put(62, 60);
		colunaBit.put(63, 61);
		colunaBit.put(64, 62);
		colunaBit.put(65, 63);
		colunaBit.put(66, 70);
		colunaBit.put(67, 90);

		colunaBit.put(68, 112);
		colunaBit.put(69, 112);
		colunaBit.put(70, 112);
		colunaBit.put(71, 112);
		colunaBit.put(72, 112);
		colunaBit.put(73, 112);
		colunaBit.put(74, 112);

		colunaBit.put(75, 124);
		colunaBit.put(76, 126);

	}
}
