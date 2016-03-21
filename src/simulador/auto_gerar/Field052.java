package simulador.auto_gerar;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Vector;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import simulador.util.*;
import simulador.interfaces.Field;
import simulador.transmissao.AutoGerar;

public class Field052 implements Field {

	ChaveCriptografiaSenha chave;
	String a;
	String b;

	public String gerar() {

		try {

			chave = new ChaveCriptografiaSenha(AutoGerar.adquirente);

			final Vector<String> componentes = chave.getComponentes();

			a = componentes.get(0);
			b = componentes.get(1);

			KeySpec keySpec = new DESedeKeySpec(
					HexadecimalUtils.convert2bytes(a + b + a));

			Key key;
			key = SecretKeyFactory.getInstance("DESede")
					.generateSecret(keySpec);
			PinBlockGenerator generator = new PinBlockGenerator(key);

			if (AutoGerar.isNotNull(AutoGerar.cartao.getPassword())) {

				final String pin = generator.generate(
						AutoGerar.cartao.getPassword(),
						AutoGerar.cartao.getPan());
				return pin;
			}

			// System.out.println("MEU PINBLOCK É: " + pin);

			return null;
		} catch (InvalidKeyException | InvalidKeySpecException
				| NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	// public static void main(String[] args) {
	//
	// IsoBase.CARTAO = Cartoes.C_6042030000000038_CHIP;
	// IsoBase.adquirente = CABAL;
	//
	// System.err.println(new Field52().gerar());
	// }
}
