package simulador.abstratas;

import java.util.Set;

import simulador.excel.padrao.Cenario;
import simulador.excel.padrao.PlanilhaAdquirente;
import simulador.excel.padrao.PlanilhaCartoes;
import simulador.excel.padrao.PlanilhaCenarios;
import simulador.excel.padrao.PlanilhaConexao;
import simulador.excel.padrao.Transacoes;
import simulador.transmissao.AutoGerar;
import simulador.transmissao.Conexao;

public abstract class ExcelPadrao extends PlanilhaTransacoes {

	private PlanilhaCenarios planilhaCenarios;
	private PlanilhaConexao planilhaConexao;
	private PlanilhaAdquirente planilhaAdquirente;
	private PlanilhaCartoes planilhaCartoes;
	public Transacoes transacoes;

	public ExcelPadrao(String localArquivo) {
		super(localArquivo);
		transacoes = new Transacoes();
		planilhaAdquirente();
		planilhaConexao();
		planilhaCartoes();
		planilhaCenarios();
	}

	public void ler() {

		final Set<Cenario> cenarios = getPlanilhaCenarios().getCenarios();

		cenarios.forEach(c -> {
			cenario = c;

			AutoGerar.cartao = getPlanilhaCartoes().getCartoes().get(c.getCartao());

			new Thread() {
				public void run() {
					popular();
				}
			}.start();

			cenarios.add(c);

			transacoes.setCenarios(cenarios);
		});

	}

	private void planilhaCenarios() {

		planilhaCenarios = new PlanilhaCenarios(localArquivo);

	}

	private void planilhaConexao() {

		planilhaConexao = new PlanilhaConexao(localArquivo);

		Conexao conexao;

		conexao = planilhaConexao.getConexao();

		// System.err.println(conexao.getChannel().getClass().getSimpleName());

		transacoes.setConexao(conexao);

	}

	private void planilhaAdquirente() {

		planilhaAdquirente = new PlanilhaAdquirente(localArquivo);

		String adquirente = planilhaAdquirente.getAdquirente();

		AutoGerar.adquirente = adquirente;

		transacoes.setAdquirente(adquirente);
	}

	private void planilhaCartoes() {

		planilhaCartoes = new PlanilhaCartoes(localArquivo);

	}

	public PlanilhaCenarios getPlanilhaCenarios() {
		return planilhaCenarios;
	}

	public PlanilhaConexao getPlanilhaConexao() {
		return planilhaConexao;
	}

	public PlanilhaAdquirente getPlanilhaAdquirente() {
		return planilhaAdquirente;
	}

	public PlanilhaCartoes getPlanilhaCartoes() {
		return planilhaCartoes;
	}

}
