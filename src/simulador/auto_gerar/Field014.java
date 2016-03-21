package simulador.auto_gerar;

import simulador.interfaces.Field;
import simulador.transmissao.AutoGerar;

public class Field014 implements Field {

	/**
	 * Metodo responsável por gerar um valor pegando hora atual + número
	 * aleatorio, bit 04 ISO8583.
	 */
	public String gerar() {

		return AutoGerar.cartao.getExpirationDate();
	}
}
