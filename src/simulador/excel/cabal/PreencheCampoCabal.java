package simulador.excel.cabal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jpos.iso.ISOUtil;

import br.com.layoutCabal.interfaces.Cabal;
import simulador.excel.padrao.Caso;
import simulador.interfaces.PreencheCampo;
import simulador.transmissao.AutoGerar;

public class PreencheCampoCabal implements PreencheCampo, Cabal {
	private static Set<String> bit48;
	private static Set<String> bit112;
	private static Set<String> bit124;
	private static Map<Integer, String> colunaTags48;
	private static Map<Integer, String> colunaTags112;
	private static Map<Integer, String> colunaTags124;

	public void preencher(Caso caso, final int numeroColuna, String conteudo, int bit) {
		try {

			if (bit != -1) {

				switch (conteudo) {

				case "AUSENTE":
					break;

				case "AUTO":
					conteudo = AutoGerar.gerarBit(bit);
					AutoGerar.bitGerados.put(bit, conteudo);
					caso.getAutorizacao().set(bit, conteudo);
					break;

				default:

					String length;

					switch (bit) {

					case 0:

						if (numeroColuna == 0) {
							caso.setNomeCaso(conteudo);
							break;
						}
						AutoGerar.bitGerados.put(bit, conteudo);
						caso.getAutorizacao().set(bit, conteudo);
						break;

					case 1:
						if (conteudo.equalsIgnoreCase("true"))
							caso.setCancelar(true);
						break;

					case 2:
						if (conteudo.equalsIgnoreCase("true"))
							caso.setDesfazer(true);
						break;

					case 48:

						final String tag48 = colunaTags48.get(numeroColuna);

						length = ISOUtil.padleft(conteudo.length() + "", 2, '0');

						bit48.add(tag48 + length + conteudo);

						conteudo = concatenarTags(bit48);

						caso.getAutorizacao().set(bit, conteudo);
						break;

					case 112:

						final String tag112 = colunaTags112.get(numeroColuna);

						length = ISOUtil.padleft(conteudo.length() + "", 3, '0');

						bit112.add(tag112 + length + conteudo);

						conteudo = concatenarTags(bit112);

						caso.getAutorizacao().set(bit, conteudo);

						break;

					case 124:

						final String tag124 = colunaTags124.get(numeroColuna);

						length = ISOUtil.padleft(conteudo.length() + "", 3, '0');

						bit124.add(tag124 + length + conteudo);

						conteudo = concatenarTags(bit124);

						caso.getAutorizacao().set(bit, conteudo);

						break;

					default:

						AutoGerar.bitGerados.put(bit, conteudo);
						caso.getAutorizacao().set(bit, conteudo);
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String concatenarTags(Set<String> tags) {
		StringBuilder s = new StringBuilder();

		tags.forEach(tag -> s.append(tag));

		return s.toString();
	}

	static {

		colunaTags48 = new HashMap<Integer, String>();
		colunaTags112 = new HashMap<Integer, String>();
		colunaTags124 = new HashMap<Integer, String>();

		bit48 = new HashSet<String>();
		bit112 = new HashSet<String>();
		bit124 = new HashSet<String>();

		colunaTags48.put(30, FIELD_048_TAG_01);
		colunaTags48.put(31, FIELD_048_TAG_02);
		colunaTags48.put(32, FIELD_048_TAG_03);
		colunaTags48.put(33, FIELD_048_TAG_04);
		colunaTags48.put(34, FIELD_048_TAG_11);
		colunaTags48.put(35, FIELD_048_TAG_12);
		colunaTags48.put(36, FIELD_048_TAG_22);
		colunaTags48.put(37, FIELD_048_TAG_23);
		colunaTags48.put(38, FIELD_048_TAG_24);
		colunaTags48.put(39, FIELD_048_TAG_25);
		colunaTags48.put(40, FIELD_048_TAG_26);
		colunaTags48.put(41, FIELD_048_TAG_27);
		colunaTags48.put(42, FIELD_048_TAG_28);
		colunaTags48.put(43, FIELD_048_TAG_29);
		colunaTags48.put(44, FIELD_048_TAG_30);
		colunaTags48.put(45, FIELD_048_TAG_31);
		colunaTags48.put(46, FIELD_048_TAG_32);
		colunaTags48.put(47, FIELD_048_TAG_33);
		colunaTags48.put(48, FIELD_048_TAG_34);
		colunaTags48.put(49, FIELD_048_TAG_35);
		colunaTags48.put(50, FIELD_048_TAG_36);
		colunaTags48.put(51, FIELD_048_TAG_37);
		colunaTags48.put(52, FIELD_048_TAG_38);
		colunaTags48.put(53, FIELD_048_TAG_39);
		colunaTags48.put(54, FIELD_048_TAG_40);
		colunaTags48.put(55, FIELD_048_TAG_43);
		colunaTags48.put(56, FIELD_048_TAG_44);

		colunaTags112.put(68, FIELD_112_TAG_001);
		colunaTags112.put(69, FIELD_112_TAG_002);
		colunaTags112.put(70, FIELD_112_TAG_010);
		colunaTags112.put(71, FIELD_112_TAG_011);
		colunaTags112.put(72, FIELD_112_TAG_012);
		colunaTags112.put(73, FIELD_112_TAG_013);
		colunaTags112.put(74, FIELD_112_TAG_014);

		colunaTags124.put(75, FIELD_112_TAG_014);

	}
}
