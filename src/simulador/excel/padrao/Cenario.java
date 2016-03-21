package simulador.excel.padrao;

import java.util.Set;

public class Cenario {

	// Set<Caso>>();

	private Set<Caso> casos;// = new HashMap<String,
	private boolean execute;
	private int quantidadeExecucao;
	private String codigoEsperado;
	private String cartao;
	private String nomeCaso;
	private String descricao;

	public String getDescricao() {
		if (descricao == null) {
			return "";
		}
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNomeCaso() {
		return nomeCaso;
	}

	public void setNomeCaso(String nomeCaso) {
		this.nomeCaso = nomeCaso;
	}

	public boolean isExecute() {
		return execute;
	}

	public void setExecute(boolean execute) {
		this.execute = execute;
	}

	public int getQuantidadeExecucao() {
		return quantidadeExecucao;
	}

	public void setQuantidadeExecucao(int quantidadeExecucao) {
		this.quantidadeExecucao = quantidadeExecucao;
	}

	public String getCodigoEsperado() {
		return codigoEsperado;
	}

	public void setCodigoEsperado(String codigoEsperado) {
		this.codigoEsperado = codigoEsperado;
	}

	public String getCartao() {
		return cartao;
	}

	public void setCartao(String cartao) {
		this.cartao = cartao;
	}

	public Set<Caso> getCasos() {
		return casos;
	}

	public void setCasos(Set<Caso> casos) {
		this.casos = casos;
	}

}
