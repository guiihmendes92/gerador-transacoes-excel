package simulador.auto_gerar;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;

import simulador.interfaces.Field;

public class Field090 implements Field {

	private ISOMsg mensagemOriginal = new ISOMsg();

	public Field090(ISOMsg messagem) {
		super();
		this.mensagemOriginal = messagem;
	}

	/**
	 * 
	 */
	public Field090() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String gerar() {

		try {

			StringBuilder sb = new StringBuilder();
			String mti;
			String nsu;
			String dataHoraGMT;
			String codigoAdquirente;

			if (mensagemOriginal.hasFields(new int[] { 0, 11, 7, 32 })) {

				mti = mensagemOriginal.getMTI();
				nsu = mensagemOriginal.getString(11);
				dataHoraGMT = mensagemOriginal.getString(7);
				codigoAdquirente = mensagemOriginal.getString(32);

				sb.append(mti);
				sb.append(nsu);
				sb.append(dataHoraGMT);
				sb.append(ISOUtil.padleft(codigoAdquirente, 11, '0'));

				return ISOUtil.padright(sb.toString(), 42, '0');

			}

		} catch (ISOException e) {
			e.printStackTrace();

			return null;
		}
		return null;

	}
}
