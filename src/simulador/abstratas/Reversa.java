package simulador.abstratas;

import java.util.HashMap;
import java.util.Map;

import org.jpos.iso.ISOMsg;

import simulador.interfaces.MontaReversa;

public abstract class Reversa implements MontaReversa {

	protected ISOMsg original;
	protected ISOMsg originalResposta;
	protected ISOMsg reversa;
	private static Map<String, String> codigoProcessamento;

	public void addCodigoProcessamento(String key, String value) {
		codigoProcessamento.put(key, value);
	}

	static {
		codigoProcessamento = new HashMap<>();
	}
}
