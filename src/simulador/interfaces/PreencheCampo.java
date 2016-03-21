package simulador.interfaces;

import simulador.excel.padrao.Caso;

public interface PreencheCampo {

	public void preencher(Caso caso, final int numeroColuna, String conteudo, int bit);

}
