package simulador.auto_gerar;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOUtil;

import simulador.interfaces.Field;

public class Field061 implements Field {

	/**
	 * Metodo responsável por gerar um valor pegando hora atual + número
	 * aleatorio, bit 04 ISO8583.
	 */
	public String gerar() {

		try {
			String s;

			s = "00090090200" + "076" + "73861222";

			return ISOUtil.padright(s, 24, ' ');
		} catch (ISOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "00090090200" + "076" + "73861222";
	}
}
