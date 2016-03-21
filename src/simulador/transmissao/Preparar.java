package simulador.transmissao;

import java.util.Set;

import br.com.cabal.layoutGetnet.interfaces.Getnet;
import simulador.abstratas.ExcelPadrao;
import simulador.excel.cabal.PlanilhaTransacoesCabal;
import simulador.excel.getnet.PlanilhaTransacoesGetnet;
import simulador.excel.padrao.Cenario;
import simulador.excel.padrao.Transacoes;

public class Preparar extends Thread implements Getnet {

	private Transacoes transacoes;

	/**
	 * 
	 */
	public Preparar() {
		super();
	}

	/**
	 * @param transacoes
	 */
	public Preparar(Transacoes transacoes) {
		super();
		this.transacoes = transacoes;
	}

	public static void main(String[] args) throws Exception {

		final String URL = "C:\\Users\\guilherme.mendes\\Desenvolvimento\\Logs\\";
		final String arquivo = "casos-teste-getnet.xlsx";
		String fileName = URL + arquivo;
		Preparar preparando = new Preparar();
		ExcelPadrao planilha;

		planilha = new PlanilhaTransacoesCabal(fileName);

		planilha = new PlanilhaTransacoesGetnet(fileName);
		// Cronometro.start();

		planilha.ler();

		// preparar.enviandoTransacao(suiteCases);

		preparando.setTransacoes(planilha.transacoes);

		preparando.enviandoTransacao();

		// Cronometro.stop();
		// Cronometro.exibeTempo("final");

	}

	public synchronized void enviandoTransacao() throws Exception {
		// Caso kase = new Caso();

		try {

			Encaminhar encaminhar = new Encaminhar();

			encaminhar.setConexao(transacoes.getConexao());

			Set<Cenario> cenarios = transacoes.getCenarios();

			cenarios.forEach(cenario -> {
				encaminhar.setCenario(cenario);

				cenario.getCasos().forEach(caso -> {
					encaminhar.setCaso(caso);

					encaminhar.enviarAutorizacao();
					new Thread(encaminhar).start();

				});
			});

		} catch (Exception e) {
			// e.printStackTrace();
			// GravaLogs.gravar(e.getMessage(), kase.getNomeCaso());
			throw new Exception("erro ao preparar Transação".toUpperCase(), e);
		}
	}

	public Transacoes getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(Transacoes transacoes) {
		this.transacoes = transacoes;
	}

}
