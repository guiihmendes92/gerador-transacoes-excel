package simulador.excel.getnet;

import simulador.excel.padrao.Caso;
import simulador.interfaces.PreencheCampo;
import simulador.transmissao.AutoGerar;

public class PreencheCampoGetnet implements PreencheCampo {

	public void preencher(Caso caso, final int numeroColuna, String conteudo, int bit) {
		try {

			if (bit != -1) {

				switch (conteudo) {

				case "AUSENTE":
					break;

				case "AUTO":
					conteudo = AutoGerar.gerarBit(bit);
					AutoGerar.bitGerados.put(bit, conteudo);
					caso.getAutorizacao().set(bit, conteudo);
					break;

				default:

					switch (numeroColuna) {

					case 0:

						if (numeroColuna == 0) {
							caso.setNomeCaso(conteudo);
							break;
						}

					case 1:
						if (conteudo.equalsIgnoreCase("true"))
							caso.setCancelar(true);
						break;

					case 2:
						if (conteudo.equalsIgnoreCase("true"))
							caso.setDesfazer(true);
						break;

					case 3:
						if (conteudo.equalsIgnoreCase("true"))
							caso.setConfirmarAutorizacao(true);
						break;

					case 4:
						if (conteudo.equalsIgnoreCase("true"))
							caso.setConfirmarEstorno(true);
						break;

					default:
						AutoGerar.bitGerados.put(bit, conteudo);
						caso.getAutorizacao().set(bit, conteudo);
						break;
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
