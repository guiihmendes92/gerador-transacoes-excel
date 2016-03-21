package simulador.auto_gerar;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOUtil;

import simulador.interfaces.Field;

public class Field032 implements Field {

	/**
	 * Metodo responsável por gerar um valor pegando hora atual + número
	 * aleatorio, bit 04 ISO8583.
	 */
	public String gerar() {
		try {

			return ISOUtil.padleft("6", 11, '0');
		} catch (ISOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
