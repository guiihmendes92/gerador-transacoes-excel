package simulador.auto_gerar;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;

import simulador.interfaces.Field;

public class Field090Getnet implements Field {

	private ISOMsg mensagemOriginal = new ISOMsg();

	public Field090Getnet(ISOMsg messagem) {
		super();
		this.mensagemOriginal = messagem;
	}

	/**
	 * 
	 */
	public Field090Getnet() {
		super();
	}

	public String gerar() {

		try {

			StringBuilder sb = new StringBuilder();
			String mti;
			String nsu;
			String dataHoraGMT;

			if (mensagemOriginal.hasFields(new int[] { 0, 11, 7 })) {

				mti = mensagemOriginal.getMTI();
				nsu = mensagemOriginal.getString(11);
				dataHoraGMT = mensagemOriginal.getString(7);

				sb.append(mti);
				sb.append(nsu);
				sb.append(dataHoraGMT);

				return ISOUtil.padright(sb.toString(), 42, '0');

			} /*
			 * else if (bitGerados.containsKey(0) || bitGerados.containsKey(11)
			 * || bitGerados.containsKey(7) || bitGerados.containsKey(32)) {
			 * 
			 * mti = bitGerados.get(0); nsu = bitGerados.get(11); dataHoraGMT =
			 * bitGerados.get(7);
			 * 
			 * sb.append(mti); sb.append(nsu); sb.append(dataHoraGMT);
			 * 
			 * J� passei por isso sei que � bem complicado, imaginando como ser� e com receio. Caso n�o d� certo com passar do tempo, tranquilo. S� tire da sua cabe�a que est� me prendendo, saiba que pode contar comigo pra qualquer coisa.
			 * 
			 * N�o quero que sinta press�o de nada...
			 * 
			 *   
			 * 
			 * return ISOUtil.padright(sb.toString(), 42, '0');
			 * 
			 * }
			 */

		} catch (ISOException e) {
			e.printStackTrace();

		}
		return null;

	}
}
