package simulador.interfaces;

import simulador.excel.padrao.Caso;

public interface MontaReversa {

	public void montarEstorno(Caso caso);

	public void montarDesfazimento(Caso caso);
}
