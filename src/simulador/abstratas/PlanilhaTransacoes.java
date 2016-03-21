package simulador.abstratas;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jpos.iso.packager.GenericPackager;

import simulador.excel.padrao.Caso;
import simulador.excel.padrao.Cenario;
import simulador.interfaces.PreencheCampo;
import simulador.util.GravaLogs;

public abstract class PlanilhaTransacoes extends Planilhas {

	public PlanilhaTransacoes(String localArquivo) {
		super(localArquivo);
		setSheet(TRANSACOES);
	}

	protected static Map<Integer, Integer> colunaBit;
	protected Set<Caso> casos;
	protected String nomeCasoTransacao;
	protected int qtdTransacao;
	protected Cenario cenario;
	private Reversa reversa;
	private PreencheCampo preencheCampo;
	private Caso caso;
	private int i;

	protected void popular() {
		try {

			// PrintStream p = System.err;

			GenericPackager packager;

			packager = new GenericPackager("src/main/resources/iso87ascii.xml");

			casos = new HashSet<Caso>();
			qtdTransacao = cenario.getQuantidadeExecucao();

			// p.append(qtdTransacao + "\tqtd cenarios ");
			// p.println("getNomeCaso: " + cenario.getNomeCaso());
			// p.println();

			linhas = planilha.rowIterator();
			linhas.next();

			while (linhas.hasNext()) {

				linha = linhas.next();

				nomeCasoTransacao = linha.getCell(0).toString();

				do {

					if (!cenario.getNomeCaso().equalsIgnoreCase(nomeCasoTransacao)) {
						break;
					}

					caso = new Caso();

					linha.forEach(coluna -> {
						caso.getAutorizacao().setPackager(packager);

						final int numeroColuna = coluna.getColumnIndex();

						String conteudo = coluna.toString();

						int bit = getBit(numeroColuna);

						getPreencheCampo().preencher(caso, numeroColuna, conteudo, bit);

					});

					if (caso.isCancelar())
						getReversa().montarEstorno(caso);

					if (caso.isDesfazer())
						getReversa().montarDesfazimento(caso);

					casos.add(caso);

				} while (1 < qtdTransacao--);

				if (!casos.isEmpty()) {
					cenario.setCasos(casos);
					i++;
					break;
				}
			}
			System.err.println("pesquisa ate achar: " + i);
		} catch (Exception e) {
			GravaLogs.geraLog(caso.getNomeCaso());
			e.printStackTrace();
		}
	}

	private int getBit(int coluna) {

		if (colunaBit.containsKey(coluna))
			return colunaBit.get(coluna);

		return -1;

	}

	public PreencheCampo getPreencheCampo() {
		return preencheCampo;
	}

	public void setPreencheCampo(PreencheCampo preencheCampo) {
		this.preencheCampo = preencheCampo;
	}

	public Reversa getReversa() {
		return reversa;
	}

	public void setReversa(Reversa reversa) {
		this.reversa = reversa;
	}
}
