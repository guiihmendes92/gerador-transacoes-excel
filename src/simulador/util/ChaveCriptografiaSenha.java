package simulador.util;

import java.util.Vector;

import simulador.constantes.Adquirentes;
import simulador.constantes.ChavesCriptografiaSenha;

public class ChaveCriptografiaSenha implements ChavesCriptografiaSenha, Adquirentes {

	private String chave;
	private String adquirente;

	/**
	 * @param adquirente
	 */
	public ChaveCriptografiaSenha(String adquirente) {
		super();
		this.adquirente = adquirente;
		chave = getChave();
	}

	private String getChave() {

		switch (adquirente) {

		case CABAL:
			return KEY_FIRSTDATA;

		case CABAL_VAN:
			return KEY_FIRSTDATA_VAN;

		case CABINT:
			return KEY_FIRSTDATA;

		case CIELO:
			return KEY_CIELO;

		case GETNET:
			return KEY_GETNET;

		case SOFTWARE_EXPRESS:
			return CABAL;

		case TECBAN:
			return KEY_TECBAN;

		case REDECARD:
			return KEY_REDECARD;

		case REDECARD_VAN:
			return KEY_REDECARD_VAN;
		case FIRSTDATA:
			return KEY_FIRSTDATA;

		case FIRSTDATA_VAN:
			return KEY_FIRSTDATA_VAN;

		default:
			System.err.println("CHAVE não mapeada".toUpperCase());
			System.exit(0);
			return null;
		}

	}

	public Vector<String> getComponentes() {
		Vector<String> componentes = new Vector<String>();

		int metade = chave.length() / 2;

		componentes.add(chave.substring(0, metade));
		componentes.add(chave.substring(metade));

		return componentes;
	}

}
