package simulador.excel.padrao;

import java.util.HashMap;
import java.util.Map;

import org.jpos.iso.BaseChannel;

import simulador.abstratas.Planilhas;
import simulador.interfaces.Channels;
import simulador.transmissao.Conexao;

public class PlanilhaConexao extends Planilhas implements Channels {
	private static Map<String, BaseChannel> channels;
	private Conexao conexao;

	// private final String PLANILHA = "Conex�o";

	/**
	 * @param workBook
	 * @param transacao
	 */
	public PlanilhaConexao(String localArquivo) {
		super(localArquivo);
		setSheet(CONEXAO);
		ler();
	}

	public void ler() {

		// Iterator<Row> linhas = planilhaConexao.rowIterator();

		linha = linhas.next();

		linha = linhas.next();

		colunas = linha.cellIterator();

		conexao = new Conexao();

		coluna = colunas.next();
		conexao.setIp(coluna.toString());

		coluna = colunas.next();
		conexao.setPorta(Integer.parseInt(coluna.toString()));

		coluna = colunas.next();
		conexao.setChannel(getChannel(coluna.toString()));

	}

	private BaseChannel getChannel(String conteudo) {

		if (channels.containsKey(conteudo))
			return channels.get(conteudo);

		return NACChannel;

	}

	public Conexao getConexao() {
		return conexao;
	}

	static {

		channels = new HashMap<String, BaseChannel>();

		channels.put(ASCIIChannel.getClass().getSimpleName(), ASCIIChannel);
		channels.put(CieloChannel.getClass().getSimpleName(), CieloChannel);
		channels.put(NACChannel.getClass().getSimpleName(), NACChannel);
		channels.put(PADChannel.getClass().getSimpleName(), PADChannel);
	}
}
