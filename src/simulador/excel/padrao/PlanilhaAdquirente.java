package simulador.excel.padrao;

import simulador.abstratas.Planilhas;
import simulador.interfaces.Channels;
import simulador.transmissao.AutoGerar;

public class PlanilhaAdquirente extends Planilhas implements Channels {
	private String adquirente;
	private String codigoAdquirente;
	private String chavePIN;

	public PlanilhaAdquirente(String localArquivo) {
		super(localArquivo);
		setSheet(ADQUIRENTE);
		ler();
	}

	public void ler() {

		linha = linhas.next();
		linha = linhas.next();

		adquirente = linha.getCell(0).toString().trim();
		codigoAdquirente = linha.getCell(1).toString().trim();
		chavePIN = linha.getCell(2).toString().trim();

		AutoGerar.adquirente = adquirente;
	}

	public String getAdquirente() {
		return adquirente;
	}

	public String getCodigoAdquirente() {
		return codigoAdquirente;
	}

	public String getChavePIN() {
		return chavePIN;
	}

}
