package simulador.transmissao;

import java.io.PrintStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jpos.iso.ISODate;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

import simulador.auto_gerar.Field002;
import simulador.auto_gerar.Field004;
import simulador.auto_gerar.Field007;
import simulador.auto_gerar.Field011;
import simulador.auto_gerar.Field012;
import simulador.auto_gerar.Field013;
import simulador.auto_gerar.Field014;
import simulador.auto_gerar.Field032;
import simulador.auto_gerar.Field035;
import simulador.auto_gerar.Field037;
import simulador.auto_gerar.Field041;
import simulador.auto_gerar.Field045;
import simulador.auto_gerar.Field049;
import simulador.auto_gerar.Field052;
import simulador.auto_gerar.Field055;
import simulador.auto_gerar.Field061;
import simulador.auto_gerar.Field090;
import simulador.auto_gerar.Field090Cielo;
import simulador.auto_gerar.Field090Getnet;
import simulador.auto_gerar.Field090RedecardVoucher;
import simulador.auto_gerar.Field090Tecban;
import simulador.cartao.Cartao;
import simulador.constantes.Adquirentes;
import simulador.interfaces.Field;
import simulador.util.GravaLogs;

public class AutoGerar implements Adquirentes {
	// public static Map<String, Object> r = new HashMap<String, Object>();
	public static Map<Integer, String> bitGerados;
	public static String adquirente;
	private static Map<Integer, Field> fields;
	private static String formatDate;
	public static Cartao cartao;
	private static ISOMsg msgOriginal;
	private static ISOMsg msgOriginalResposta;

	static {

		bitGerados = new HashMap<Integer, String>();

		fields = new HashMap<Integer, Field>();

		fields.put(2, new Field002());
		fields.put(4, new Field004());
		fields.put(7, new Field007());
		fields.put(11, new Field011());
		fields.put(12, new Field012());
		fields.put(13, new Field013());
		fields.put(14, new Field014());
		fields.put(32, new Field032());
		fields.put(35, new Field035());
		fields.put(37, new Field037());
		fields.put(41, new Field041());
		fields.put(45, new Field045());
		fields.put(49, new Field049());
		fields.put(52, new Field052());
		fields.put(55, new Field055());
		fields.put(61, new Field061());

	}

	public static String gerarBit(int bit) {

		if (fields.containsKey(bit)) {

			final String valor = fields.get(bit).gerar();

			bitGerados.put(bit, valor);

			return valor;

		} else if (bit == 90) {

			fields.put(90, getField90());

			final String valor = fields.get(bit).gerar();

			bitGerados.put(bit, valor);

			return valor;
		}

		return null;
	}

	public static ISOMsg setFields(ISOMsg msg, final int[] bits) throws ISOException {

		for (Integer bit : bits)
			msg.set(bit, gerarBit(bit));

		return msg;

	}

	private static Field getField90() {

		if (isNotNull(AutoGerar.adquirente)) {

			switch (AutoGerar.adquirente) {

			case CABAL:
				return new Field090(msgOriginal);

			case GETNET:
				return new Field090Getnet(msgOriginal);

			case REDECARD_VAN:
				return new Field090RedecardVoucher(msgOriginal);

			case TECBAN:
				return new Field090Tecban(msgOriginal);

			case CIELO:
				return new Field090Cielo(msgOriginal, msgOriginalResposta);

			default:
				return new Field090();
			}

		}
		return null;
	}

	public static String gerarBit37Cielo(ISOMsg msg) {
		return msg.getString(41).substring(0, 2) + msg.getString(41).substring(4, 8) + msg.getString(11);

	}

	// public synchronized static void verificarCampos(ISOMsg message,
	// Set<Integer> validFields) {
	// new CheckFields(message, validFields);
	//
	// }

	public synchronized void dump(ISOMsg mensagem, String tipoMsg, String classeTransacao) throws Exception {

		try {

			formatDate = getDataHoraMilisegundos();

			if (!mensagem.getChildren().isEmpty()) {

				StringBuilder sb = new StringBuilder("\n");

				sb.append(formatDate).append("\n");
				sb.append(new String(mensagem.pack()));

				System.out.println(sb.toString());

				GravaLogs.gravar(sb, classeTransacao);
				System.err.println(tipoMsg.toUpperCase());
				mensagem.dump(GravaLogs.geraLog(classeTransacao), "");
				mensagem.dump(System.out, "");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getDataHoraMilisegundos() {
		return ISODate.formatDate(new Date(), "dd/MM/yyyy HH:mm:ss,SSS");
	}

	public static void posicaoString(String s) {
		PrintStream p = new PrintStream(System.out);
		int i = 0;

		p.println("Posi��o: caracter\n");

		for (char c : s.toCharArray()) {
			p.println(i + "\t: " + c);
			i++;
		}

	}

	public static boolean isNotNull(Object ob) {
		return ob != null;
	}

	public static boolean isNull(Object ob) {
		return ob == null;
	}

	public static void setMsgOriginal(ISOMsg msgOriginal) {
		AutoGerar.msgOriginal = msgOriginal;
	}

	public static void setMsgOriginalResposta(ISOMsg msgOriginalResposta) {
		AutoGerar.msgOriginalResposta = msgOriginalResposta;
	}

}
