package simulador.excel.padrao;

import java.util.Set;

import simulador.transmissao.Conexao;

public class Transacoes {

	private Set<Cenario> cenarios;
	private Conexao conexao;
	private String adquirente;

	public Set<Cenario> getCenarios() {
		return cenarios;
	}

	public void setCenarios(Set<Cenario> cenarios) {
		this.cenarios = cenarios;
	}

	public Conexao getConexao() {
		return conexao;
	}

	public void setConexao(Conexao conexao) {
		this.conexao = conexao;
	}

	public String getAdquirente() {
		return adquirente;
	}

	public void setAdquirente(String adquirente) {
		this.adquirente = adquirente;
	}

}
