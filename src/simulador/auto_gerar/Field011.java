package simulador.auto_gerar;

import java.util.Random;

import org.jpos.iso.ISOUtil;

import simulador.interfaces.Field;
import simulador.util.Padder;
import simulador.util.Sequencer;

public class Field011 implements Field {

	/**
	 * Metodo responsável por gerar um número de 6 posições incrementando + 1 a
	 * cada execução de acordo com valor que está no arquivo "NSU_REDES", bit 11
	 * ISO8583.
	 */

	public String gerar1() {
		Sequencer sequencer;
		sequencer = Sequencer.compile("NSU_REDES");
		return Padder.leftPad(sequencer.nextInt() + "", 6, '0');
	}

	public String gerar() {
		// Cronometro.start();
		// List<Integer> numeros = new ArrayList<Integer>();
		//
		// for (int i = 1; i < 999999; i++) {
		// numeros.add(i);
		// }

		// Collections.shuffle(numeros);
		// Cronometro.stop();
		// Cronometro.exibeTempo("Bit11:");
		// return Padder.leftPad(new Random().nextInt(1000001) + "", 6, '0');
		return ISOUtil.getRandomDigits(new Random(), 6, 9);
		// return Padder.leftPad(numeros.listIterator().next() + "", 6, '0');
	}

}
