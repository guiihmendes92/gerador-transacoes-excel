package simulador.auto_gerar;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;

import simulador.interfaces.Field;

public class Field090Cielo implements Field {

	private ISOMsg mensagemOriginal;
	private ISOMsg resposta;

	public Field090Cielo(ISOMsg mensagemOriginal, ISOMsg resposta) {
		super();
		this.mensagemOriginal = mensagemOriginal;
		this.resposta = resposta;
	}

	public String gerar() {

		StringBuilder sb = new StringBuilder();
		String nsuEmissor = null;

		try {

			nsuEmissor = resposta.getString(38) != null ? resposta
					.getString(38) : ISOUtil.padleft("", 6, ' ');

			if (mensagemOriginal.hasFields(new int[] { 0, 11, 12, 13 })) {

				sb.append(mensagemOriginal.getMTI());
				sb.append(nsuEmissor);
				sb.append(mensagemOriginal.getString(13));
				sb.append(mensagemOriginal.getString(12));
				sb.append(mensagemOriginal.getString(11));

				return sb.toString();
			}

		} catch (ISOException e) {

			System.out.println("Erro ao montar o bit 90 motivo: "
					+ e.getMessage());
			e.printStackTrace();

		}
		return null;
	}
}
