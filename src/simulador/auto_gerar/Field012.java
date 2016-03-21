package simulador.auto_gerar;

import java.util.Date;

import org.jpos.iso.ISODate;

import simulador.interfaces.Field;

public class Field012 implements Field {

	/**
	 * Metodo responsável por devolver hora local, bit 12 ISO8583.
	 * 
	 * @return "HHmmss"
	 */
	@SuppressWarnings("deprecation")
	public String gerar() {
		return ISODate.formatDate(
				ISODate.parseDateTime(new Date().toLocaleString()), "HHmmss");
	}

}
