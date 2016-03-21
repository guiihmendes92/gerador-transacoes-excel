package simulador.auto_gerar;

import java.util.Date;
import java.util.TimeZone;

import org.jpos.iso.ISODate;

import simulador.interfaces.Field;

public class Field007 implements Field {

	/**
	 * Metodo responsável por devolver data e hora GMT, bit 07 ISO8583.
	 * @return "MMddHHmmss"
	 */

	public String gerar() {
		return ISODate.formatDate(new Date(), "MMddHHmmss",
				TimeZone.getTimeZone("GMT"));
	}


}
