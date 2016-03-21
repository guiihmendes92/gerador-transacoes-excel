package simulador.excel.getnet;

import java.util.HashMap;

import simulador.abstratas.ExcelPadrao;

public class PlanilhaTransacoesGetnet extends ExcelPadrao {

	public PlanilhaTransacoesGetnet(String nomePlanilha) {
		super(nomePlanilha);
		setPreencheCampo(new PreencheCampoGetnet());
		setReversa(new ReversaGenet());
	}

	static {

		colunaBit = new HashMap<Integer, Integer>();

		colunaBit.put(0, 0);
		colunaBit.put(1, 1);
		colunaBit.put(2, 3);
		colunaBit.put(3, 3);
		colunaBit.put(4, 4);

		colunaBit.put(5, 0);
		colunaBit.put(6, 2);
		colunaBit.put(7, 3);
		colunaBit.put(8, 4);
		colunaBit.put(9, 7);
		colunaBit.put(10, 11);
		colunaBit.put(11, 12);
		colunaBit.put(12, 13);
		colunaBit.put(13, 14);
		colunaBit.put(14, 18);
		colunaBit.put(15, 22);
		colunaBit.put(16, 23);
		colunaBit.put(17, 35);
		colunaBit.put(18, 39);
		colunaBit.put(19, 41);
		colunaBit.put(20, 42);
		colunaBit.put(21, 49);
		colunaBit.put(22, 52);
		colunaBit.put(23, 55);
		colunaBit.put(24, 61);
		colunaBit.put(25, 62);
		colunaBit.put(26, 63);
		colunaBit.put(27, 67);
		colunaBit.put(28, 90);
		colunaBit.put(29, 125);
		colunaBit.put(30, 127);

	}

}
