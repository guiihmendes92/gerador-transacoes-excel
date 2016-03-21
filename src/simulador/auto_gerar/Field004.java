package simulador.auto_gerar;

import java.util.Date;
import java.util.Random;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOUtil;

import simulador.interfaces.Field;

public class Field004 implements Field {

	/**
	 * Metodo responsável por gerar um valor pegando hora atual + número
	 * aleatorio, bit 04 ISO8583.
	 */
	public String gerar() {
		try {

			Date d = new Date();
			@SuppressWarnings("deprecation")
			String valor = "" + d.getHours() + new Random().nextInt(100);

			return ISOUtil.padleft(valor, 12, '0');

		} catch (ISOException e) {
			return "1";
		}
	}
}
