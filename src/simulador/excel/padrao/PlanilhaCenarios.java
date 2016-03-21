package simulador.excel.padrao;

import java.util.HashSet;
import java.util.Set;

import simulador.abstratas.Planilhas;

public class PlanilhaCenarios extends Planilhas {

	private Set<Cenario> cenarios;

	/**
	 * @param workBook
	 */
	public PlanilhaCenarios(String localArquivo) {
		super(localArquivo);
		setSheet(CENARIOS);
		ler();
	}

	public void ler() {

		linha = linhas.next();

		cenarios = new HashSet<Cenario>();

		linhas.forEachRemaining(linha -> {
			Cenario cenario = new Cenario();

			linha.forEach(coluna -> {

				final int numeroColuna = coluna.getColumnIndex();

				String conteudo = coluna.toString().trim();

				// p.println(numeroColuna + " " + conteudo);

				preencherCenario(cenario, numeroColuna, conteudo);

			});

			if (cenario.isExecute()) {
				cenarios.add(cenario);

			}

		});

		while (linhas.hasNext()) {

			linha = linhas.next();

			// colunas = linha.cellIterator();

			Cenario cenario = new Cenario();

			for (colunas = linha.cellIterator(); colunas.hasNext();) {

				coluna = colunas.next();

				final int numeroColuna = coluna.getColumnIndex();

				String conteudo = coluna.toString().trim();

				// p.println(numeroColuna + " " + conteudo);

				preencherCenario(cenario, numeroColuna, conteudo);

			}

			if (cenario.isExecute()) {
				cenarios.add(cenario);

			}

		}
		// System.err.println(cenarios+"\n");
		//
		// for (Cenario cenario : cenarios.values()) {
		//
		// System.err.println(cenario.getNomeCaso());
		// System.err.println(cenario.getQuantidadeExecucao());
		// System.err.println();
		// }

	}

	private void preencherCenario(Cenario cenario, final int numeroColuna, String conteudo) {

		switch (numeroColuna) {

		case 0:
			cenario.setNomeCaso(conteudo);
			break;

		case 1:
			if (conteudo.isEmpty())
				break;
			cenario.setQuantidadeExecucao(Integer.parseInt(conteudo));
			break;

		case 2:
			if (conteudo.equalsIgnoreCase("true"))
				cenario.setExecute(true);
			break;

		case 3:
			cenario.setCodigoEsperado(conteudo);
			break;

		case 4:
			cenario.setCartao(conteudo);
			break;

		default:
			cenario.setDescricao(conteudo);
			break;
		}
	}

	public Set<Cenario> getCenarios() {
		return cenarios;
	}

	public void setCenarios(Set<Cenario> cenarios) {
		this.cenarios = cenarios;
	}

}
