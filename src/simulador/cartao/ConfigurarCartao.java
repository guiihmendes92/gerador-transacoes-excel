package simulador.cartao;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOUtil;

public class ConfigurarCartao {

	private final char TRACK1_SEPARATOR = '^';
	private final char TRACK2_SEPARATOR = '=';
	private final char FORMAT_CODE = 'B';
	private final int LENGTH_BIN = 6;
	// private final int MINPANLEN = 10;
	private final int LENGTH_CARD_HOLDER = 26;
	private final int LENGTH_TRACK1 = 65;
	private final int LENGTH_TRACK2 = 37;
	private final int LENGTH_PAN = 16;
	private Cartao cartao = new Cartao();

	/**
	 * @param cartao
	 */
	public ConfigurarCartao(Cartao cartao) {
		super();
		this.cartao = cartao;

		montaTrack1();

		montaTrack2();

		montaBin();

		montaBinExtended();
	}

	public Cartao configurar() {

		return cartao;
	}

	private void montaBinExtended() {
		final String pan = cartao.getPan();

		if (!(pan.length() < LENGTH_BIN + 2)) {

			String binExtended = pan.substring(0, 8);

			cartao.setBinExtended(binExtended);
		}
	}

	private void montaBin() {
		final String pan = cartao.getPan();

		if (!(pan.length() < LENGTH_BIN)) {

			String bin = pan.substring(0, 6);

			cartao.setBin(bin);
		}
	}

	private void montaTrack1() {

		try {
			final String expirationDate = cartao.getExpirationDate();
			final String serviceCode = cartao.getServiceCode();
			final String cvv1 = cartao.getCvv1();
			String pan = cartao.getPan();
			String cardHolder = cartao.getCardHolder();// == null ? "":
														// cartao.getCardHolder();

			pan = ISOUtil.padright(pan, LENGTH_PAN, ' ');
			cardHolder = ISOUtil.padright(cardHolder, LENGTH_CARD_HOLDER, ' ');

			final String track1 = FORMAT_CODE + pan + TRACK1_SEPARATOR + cardHolder.toUpperCase() + TRACK1_SEPARATOR
					+ expirationDate + serviceCode + cvv1;

			this.cartao.setTrack1(ISOUtil.padright(track1, LENGTH_TRACK1, '0'));

		} catch (ISOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void montaTrack2() {

		try {
			final String expirationDate = cartao.getExpirationDate();
			final String serviceCode = cartao.getServiceCode();
			final String cvv1 = cartao.getCvv1();
			String pan = cartao.getPan();

			pan = ISOUtil.padright(pan, LENGTH_PAN, ' ');

			final String track1 = pan + TRACK2_SEPARATOR + expirationDate + serviceCode + cvv1;

			this.cartao.setTrack2(ISOUtil.padright(track1, LENGTH_TRACK2, '0'));

		} catch (ISOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public class InvalidCardException extends Exception {

		private static final long serialVersionUID = -7842869068559382395L;

		public InvalidCardException() {
			super();
		}

		public InvalidCardException(String s) {
			super(s);
		}
	}
}
