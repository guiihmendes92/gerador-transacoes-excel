package simulador.auto_gerar;

import simulador.interfaces.Field;
import simulador.transmissao.AutoGerar;

public class Field002 implements Field {

	public String gerar() {

		try {

			return AutoGerar.cartao.getPan();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;// TODO: handle exception
		}
	}
}
