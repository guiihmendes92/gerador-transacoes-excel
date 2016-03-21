package simulador.excel.cabal;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

import br.com.layoutCabal.interfaces.Cabal;
import simulador.abstratas.Reversa;
import simulador.excel.padrao.Caso;
import simulador.transmissao.AutoGerar;

public class ReversaCabal extends Reversa implements Cabal {

	/**
	 * @param caso
	 */

	public void montarEstorno(Caso caso) {

		try {

			original = caso.getAutorizacao();
			reversa = caso.getEstorno();
			originalResposta = caso.getRespostaAutorizacao();
			AutoGerar.setMsgOriginal(original);

			reversa.setPackager(original.getPackager());

			reversa.setMTI(MTI_0400);

			reversa.set(3, ESTORNO_COMPRA_CREDITO);

			final int[] bits = new int[] { 7, 11, 12, 13 };

			AutoGerar.setFields(reversa, bits);

			reversa.merge((ISOMsg) originalResposta.clone(new int[] { 38 }));

			reversa.merge((ISOMsg) original.clone(new int[] //
			{ 4, 22, 32, 35, 41, 42, 48, 49 }));

			reversa.set(90, AutoGerar.gerarBit(90));

			caso.setEstorno(reversa);

		} catch (ISOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void montarDesfazimento(Caso caso) {
		try {
			original = caso.getAutorizacao();
			reversa = caso.getEstorno();
			originalResposta = caso.getRespostaAutorizacao();
			AutoGerar.setMsgOriginal(original);

			reversa.setPackager(original.getPackager());

			reversa.setMTI(MTI_0420);

			reversa.merge((ISOMsg) originalResposta.clone(new int[] { 38, 39 }));

			reversa.set(90, AutoGerar.gerarBit(90));

			reversa.merge((ISOMsg) original.clone(new int[]//
			{ 3, 4, 7, 11, 12, 13, 22, 35, 37, 41, 42, 48, 49 }));

			caso.setDesfazimento(reversa);

		} catch (ISOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
