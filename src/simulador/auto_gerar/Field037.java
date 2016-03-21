package simulador.auto_gerar;

import java.util.Random;

import org.jpos.iso.ISOUtil;

import simulador.interfaces.Field;

public class Field037 implements Field {

	public String gerar() {

		// Integer bit37 = Math.abs(new Random().nextInt()) % 1000000000;

		// return Padder.leftPad(bit37.toString(), 12, '0');
		return ISOUtil.getRandomDigits(new Random(), 12, 9);

	}

	public static void main(String[] args) {

	}

}
