package simulador.auto_gerar;

import java.util.Date;

import org.jpos.iso.ISODate;

import simulador.interfaces.Field;

public class Field013 implements Field {

	/**
	 * Metodo responsável por devolver data local, bit 13 ISO8583.
	 * 
	 * @return "MMdd"
	 */
	public String gerar() {
		return ISODate.formatDate(new Date(), "MMdd");
	}


}
