package simulador.util;

import java.security.Key;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

public class PinBlockGenerator {
	private final String ALGORITHM;
	private final Key SECRET_KEY;
	private final Cipher CIPHER;

	public PinBlockGenerator(Key key) {
		this.SECRET_KEY = key;
		try {
			ALGORITHM = SECRET_KEY.getAlgorithm();
			CIPHER = Cipher.getInstance(ALGORITHM);

			CIPHER.init(Cipher.ENCRYPT_MODE, SECRET_KEY);

			// System.out.println(convert2Hexadecimal(SECRET_KEY.getEncoded()));

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public String generate(String plainText, String cardNumber) {
		if (ALGORITHM.equals("DES"))
			return generateDES(plainText);

		String block01 = Padder.rightPad("04" + plainText, 16, 'F');
		String block02 = "0" + cardNumber;
		block02 = "0000" + block02.substring(4, 16);

		byte[] block01_b = HexadecimalUtils.convert2bytes(block01);
		byte[] block02_b = HexadecimalUtils.convert2bytes(block02);
		String pinblock = "";
		try {
			pinblock = HexadecimalUtils.convert2Hexadecimal(encrypt(xor(
					block01_b, block02_b)));
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return pinblock.substring(0, 16).toUpperCase();
	}

	private String fillIt(String str) {
		if (str.length() < 16)
			return fillIt("30" + str);
		return str;
	}

	private String generateDES(String plainText) {
		String hexadecimalText = "";
		for (char ch : plainText.toCharArray())
			hexadecimalText += Integer.toHexString(ch);
		hexadecimalText = fillIt(hexadecimalText);

		byte[] block = HexadecimalUtils.convert2bytes(hexadecimalText);

		String pinblock = "";
		try {
			pinblock = HexadecimalUtils.convert2Hexadecimal(encrypt(block));
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return pinblock.substring(0, 16).toUpperCase();
	}

	private byte[] xor(byte[] block01, byte[] block02) {
		byte[] result = new byte[8];

		for (int i = 0; i < 8; i++)
			result[i] = (byte) (((int) block01[i]) ^ ((int) block02[i]));

		return result;
	}

	private byte[] encrypt(byte[] pinblock) throws IllegalBlockSizeException,
			BadPaddingException {
		return CIPHER.doFinal(pinblock);
	}

	public static String convert2Hexadecimal(byte[] encoded) {
		String str = "";
		for (byte b : encoded) {
			byte[] ch = { (byte) (b & 0xff) };
			str += HexadecimalUtils.convert2Hexadecimal(ch);
		}
		return str;
	}

}