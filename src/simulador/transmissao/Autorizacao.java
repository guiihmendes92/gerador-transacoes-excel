package simulador.transmissao;

import simulador.abstratas.Transmitir;
import simulador.excel.padrao.Caso;
import simulador.util.Cronometro;

public class Autorizacao extends Transmitir {

	public Autorizacao(Caso caso) {
		super(caso);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void enviar() {

	}

	@Override
	public void receber() {
		try {
			resposta = channel.receive();

			// p.println(getChannel().countObservers());

			Cronometro.stop();

			caso.setRespostaAutorizacao(resposta);

			dump(resposta, caso.getNomeCaso());

			Cronometro.exibeTempo("Resposta: ".toUpperCase());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
