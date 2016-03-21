package simulador.auto_gerar;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;

import simulador.interfaces.Field;

public class Field090RedecardVoucher implements Field {

	private ISOMsg mensagemOriginal;

	public Field090RedecardVoucher(ISOMsg messagem) {
		super();
		this.mensagemOriginal = messagem;
	}

	public String gerar() {

		StringBuilder sb = new StringBuilder();

		try {

			if (mensagemOriginal.hasFields(new int[] { 0, 11, 12, 13, 32 })) {

				sb.append(mensagemOriginal.getMTI());
				sb.append(mensagemOriginal.getString(11));
				sb.append(mensagemOriginal.getString(13));
				sb.append(mensagemOriginal.getString(12));
				sb.append(ISOUtil.padleft(mensagemOriginal.getString(32), 11,
						'0'));
				return ISOUtil.padright(sb.toString(), 42, '0');
			}

		} catch (ISOException e) {

			System.out.println("Erro ao montar o bit 90 motivo: "
					+ e.getMessage());
			e.printStackTrace();

		}
		return null;

	}
}
