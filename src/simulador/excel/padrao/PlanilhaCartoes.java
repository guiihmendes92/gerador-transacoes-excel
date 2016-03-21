package simulador.excel.padrao;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import simulador.abstratas.Planilhas;
import simulador.cartao.Cartao;
import simulador.cartao.ConfigurarCartao;

public class PlanilhaCartoes extends Planilhas {

	private Map<String, Cartao> cartoes;
	Cartao cartao;

	/**
	 * @param workBook
	 * @param pCartoes
	 * @param transacao
	 */
	public PlanilhaCartoes(String localArquivo) {
		super(localArquivo);
		setSheet(CARTOES);
		ler();
	}

	public static void main(String[] args) throws IOException {
		final String URL = "C:\\Users\\guilherme.mendes\\Desenvolvimento\\Logs\\";

		final String nomeArquivo = "casos-teste-getnet.xlsx";

		String localArquivo = URL + nomeArquivo;

		// FileInputStream leituraArquivo = new FileInputStream(new
		// File(fileName));

		// Workbook workBook = new XSSFWorkbook(leituraArquivo);

		PlanilhaCartoes pc = new PlanilhaCartoes(localArquivo);

		pc.getCartoes();

		Collection<Cartao> cartoes = pc.getCartoes().values();

		cartoes.forEach(cartao -> {

			// for (Cartao cartao : cartoes) {

			System.out.println(cartao.getNome());
			System.out.println(cartao.getPan());
			System.out.println(cartao.getTrack1());
			System.out.println(cartao.getTrack2());
			System.out.println(cartao.getPassword());
			System.out.println(cartao.getExpirationDate());
			System.out.println(cartao.getBinExtended());
			System.out.println(cartao.getBin());
			System.out.println(cartao.getCvv1());
			System.out.println(cartao.getCvv2());
			System.out.println(cartao.getServiceCode());
			System.out.println(cartao.getPositiveIdentity());
			System.err.println("Proximo");
			System.err.println();
			// }

		});
	}

	public void ler() {

		try {

			cartoes = new HashMap<String, Cartao>();

			linha = linhas.next();

			linhas.forEachRemaining(linha -> {
				cartao = new Cartao();

				linha.forEach(coluna -> {
					String conteudo = coluna.toString().trim();

					int numeroColuna = coluna.getColumnIndex();

					preencherCartao(cartao, numeroColuna, conteudo);
					// }

				});

				cartao = new ConfigurarCartao(cartao).configurar();

				cartoes.put(cartao.getNome(), cartao);

			});

			// while (linhas.hasNext()) {
		} catch (Exception e) {
			e.printStackTrace(); // TODO: handle exception
		}
		//
		// cartao = new Cartao();
		//
		// linha = linhas.next();
		//
		// for (colunas = linha.cellIterator(); colunas.hasNext();) {
		//
		// coluna = colunas.next();
		//
		// final int numeroColuna = coluna.getColumnIndex();
		//
		// String conteudo = coluna.toString().trim();
		//
		// preencherCartao(cartao, numeroColuna, conteudo);
		// // p.println(numeroColuna + " " + conteudo);
		//
		// }
		//
		// cartao = new ConfigurarCartao(cartao).getCartao();
		//
		// cartoes.put(cartao.getNome(), cartao);
		//
		// }

	}

	private void preencherCartao(Cartao cartao, final int numeroColuna, String conteudo) {

		switch (numeroColuna) {

		case 0:
			cartao.setNome(conteudo);
			break;

		case 1:
			cartao.setPan(conteudo);
			break;

		case 2:
			cartao.setCardHolder(conteudo);
			break;

		case 3:
			cartao.setExpirationDate(conteudo);
			break;

		case 4:
			cartao.setServiceCode(conteudo);
			break;

		case 5:
			cartao.setCvv1(conteudo);
			break;

		case 6:
			cartao.setCvv2(conteudo);
			break;

		case 7:
			cartao.setPositiveIdentity(conteudo);
			break;

		default:
			cartao.setPassword(conteudo);
			break;
		}
	}

	public Map<String, Cartao> getCartoes() {
		return cartoes;
	}

	public void setCartoes(Map<String, Cartao> cartoes) {
		this.cartoes = cartoes;
	}

}
