package simulador.excel.getnet;

import org.jpos.iso.ISOMsg;

import br.com.cabal.layoutGetnet.interfaces.Getnet;
import simulador.abstratas.Reversa;
import simulador.excel.padrao.Caso;
import simulador.transmissao.AutoGerar;

public class ReversaGenet extends Reversa implements Getnet {

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

			reversa.set(3, ESTORNO_CREDITO);

			final int[] bits = new int[] { 7, 11, 12, 13 };

			AutoGerar.setFields(reversa, bits);

			reversa.set(90, AutoGerar.gerarBit(90));

			reversa.merge((ISOMsg) originalResposta.clone(new int[]//
			{ 127 }));

			reversa.merge((ISOMsg) original.clone(new int[] //
			{ 0, 4, 22, 32, 35, 41, 42, 49 }));

			caso.setEstorno(reversa);
		} catch (Exception e) {
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

			reversa.merge((ISOMsg) original.clone(new int[]//
			{ 3, 4, 7, 11, 12, 13, 22, 35, 41, 42, 49 }));

			reversa.merge((ISOMsg) originalResposta.clone(new int[]//
			{ 127 }));

			reversa.set(90, AutoGerar.gerarBit(90));

			caso.setDesfazimento(reversa);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
