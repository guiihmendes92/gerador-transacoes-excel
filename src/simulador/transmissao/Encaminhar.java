package simulador.transmissao;

import java.io.IOException;
import java.io.PrintStream;
import java.net.SocketException;
import java.util.Date;

import org.jpos.iso.BaseChannel;
import org.jpos.iso.ISODate;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

import simulador.excel.padrao.Caso;
import simulador.excel.padrao.Cenario;
import simulador.util.Cronometro;
import simulador.util.GravaLogs;
import br.com.cabal.layoutGetnet.layout.ValidaFieldsGetnet;

public class Encaminhar implements Runnable {

	private Cenario cenario;
	private Caso caso = new Caso();
	private Conexao conexao = new Conexao();
	private BaseChannel channel;
	private PrintStream p = new PrintStream(System.out);
	private ISOMsg autorizacao;
	private ISOMsg desfazimento;
	private ISOMsg estorno;
	private ISOMsg confirmacao = new ISOMsg();
	private ISOMsg resposta = new ISOMsg();
	private ISOMsg respostaSonda = new ISOMsg();
	private String formatDate;

	private ValidaFieldsGetnet layout = new ValidaFieldsGetnet();

	@Override
	public void run() {

		try {

			if (channel.isConnected()) {

				receberAutorizacao();

				String bit39 = resposta.getString(39);
				boolean isAprovada = "00".equals(bit39);
				String codigoEsperado = cenario().getCodigoEsperado();

				if (caso().isConfirmarAutorizacao() && isAprovada) {

					montaConfirmacao();
					caso.setConfirmaAutorizacao(confirmacao);
					send(confirmacao);

				}

				if (!codigoEsperado.equals(bit39)) {
					p.println();
					p.print("CODIGO ESPERADO:\t");
					p.println(codigoEsperado);
					p.print("CODIGO RETORNADO:\t");
					p.println(bit39);
				}
				receberSonda();

				if (isAprovada && caso().isCancelar()) {

					Thread.sleep(3000);
					enviarEstorno();
					receberEstorno();

					if (caso().isConfirmarEstorno() && isAprovada && caso.getEstorno().hasFields()) {
						// Thread.sleep(3000);
						montaConfirmacao();
						caso.setConfirmaEstorno(confirmacao);
						send(confirmacao);

					}
					receberSonda();

					if (respostaSonda.hasFields()) {
						send(respostaSonda);
					}

				}

				if (isAprovada && caso().isDesfazer()) {

					enviarDesfazimento();
					receberDesfazimento();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void receberSonda() {

		new Thread() {

			@Override
			public void run() {

				try {
					receberSonda(channel.receive());
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}.start();
	}

	private void montaConfirmacao() throws Exception {

		confirmacao.setPackager(resposta.getPackager());
		confirmacao.setMTI("0202");
		confirmacao.merge((ISOMsg) resposta.clone(new int[]//
		{ 3, 4, 11, 12, 13, 39, 41, 42, 49, 127 }));
		confirmacao.set(7, AutoGerar.gerarBit(7));

		layout.validarLayout(confirmacao);
	}

	public synchronized void enviarAutorizacao() {

		try {
			autorizacao = caso.getAutorizacao();

			Cronometro.start();

			send(autorizacao);
			layout.validarLayout(autorizacao);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void receberAutorizacao() throws Exception {
		try {
			resposta = channel.receive();

			// p.println(getChannel().countObservers());

			Cronometro.stop();

			caso.setRespostaAutorizacao(resposta);

			printConexao();

			dump(resposta, cenario().getNomeCaso());

			layout.validarLayout(resposta);

			Cronometro.exibeTempo("Resposta: ".toUpperCase());

		} catch (Exception e) {
			p.print(e.getMessage());// TODO: handle exception
		}
	}

	public synchronized void enviarDesfazimento() throws Exception {

		Cronometro.start();

		desfazimento = caso.getDesfazimento();

		send(desfazimento);

		layout.validarLayout(desfazimento);

	}

	public synchronized void receberDesfazimento() throws Exception {

		try {

			resposta = channel.receive();
		} catch (Exception e) {
			System.err.println(e.getMessage());// TODO: handle exception
		}

		caso.setRespostaDesfazimento(resposta);

		Cronometro.stop();

		dump(resposta, cenario().getNomeCaso());
		layout.validarLayout(resposta);

		Cronometro.exibeTempo("Resposta Desfazimento: ".toUpperCase());

	}

	public synchronized void enviarEstorno() throws Exception {

		Cronometro.start();

		estorno = caso.getEstorno();

		estorno.merge((ISOMsg) resposta.clone(new int[]//
		{ 127 }));

		send(estorno);

		layout.validarLayout(estorno);

	}

	public synchronized void receberEstorno() throws Exception {

		resposta = channel.receive();

		// autorizacao.setr

		caso.setRespostaEstorno(resposta);

		Cronometro.stop();

		dump(resposta, cenario().getNomeCaso());

		layout.validarLayout(resposta);

		Cronometro.exibeTempo("Resposta Estorno: ".toUpperCase());

	}

	public synchronized void receberSonda(ISOMsg msg) throws Exception {
		if ("0600".equals(msg.getMTI())) {

			msg.dump(p, "");

			layout.validarLayout(msg);

			respostaSonda = montaRespostaSonda(msg);

			// Thread.sleep(30000);
			send(respostaSonda);

		}
	}

	private ISOMsg montaRespostaSonda(ISOMsg msg) throws ISOException {

		// if (msg.hasFields(new int[]//
		// { 0, 3, 7, 11, 12, 13, 41, 42, 125, 127 })) {
		//
		// }

		msg.set(39, "00");
		msg.setResponseMTI();

		layout.validarLayout(msg);

		return msg;
	}

	public synchronized void send(ISOMsg msg) throws Exception {

		if (!channel.isConnected()) {

			conectar();

		}

		if (channel.isConnected()) {

			printConexao();
			dump(msg, cenario().getNomeCaso());

			channel.send(msg);
		}

	}

	private synchronized void conectar() throws SocketException, IOException, ISOException {

		try {

			channel.setHost(conexao().getIp(), conexao().getPorta());
			channel.setPackager(conexao().getPackager());
			channel.setTimeout(conexao().getTimeout());

			channel.connect();

			p.println("Conectou".toUpperCase());

		} catch (Exception e) {

			printConexao();
			System.err.println(e.getMessage());//
		}
	}

	public void sendStringMsg(String msg, Conexao conexoes) throws Exception {

		conexao = conexoes;
		channel = conexao.getChannel();

		ISOMsg msgIso = new ISOMsg();
		msgIso.setPackager(conexao.getPackager());
		msgIso.unpack(msg.getBytes());

		dump(msgIso, conexoes.getClass().getSimpleName());

		if (!channel.isConnected()) {
			// channel.setHost(conexao.getIp(), conexao.getPorta());
			// channel.setPackager(conexao.getPackager());
			// channel.setTimeout(conexao.getTimeout());
			channel.connect();

		} else if (channel.isConnected()) {

			channel.send(msgIso);

			dump(channel.receive(), conexoes.getClass().getSimpleName());
		}

	}

	public synchronized void dump(ISOMsg mensagem, String tipoTransacao) throws Exception {

		formatDate = ISODate.formatDate(new Date(), "dd/MM/yyyy HH:mm:ss,SSS");

		if (!mensagem.getChildren().isEmpty()) {

			// PrintStream p = new PrintStream(System.out);

			StringBuilder sb = new StringBuilder("\n");

			// p.println(formatDate);
			sb.append(formatDate).append("\n");
			sb.append(new String(mensagem.pack()));
			System.out.println(sb.toString());

			// p.to
			GravaLogs.gravar(sb, tipoTransacao);
			mensagem.dump(GravaLogs.geraLog(tipoTransacao), "");
			mensagem.dump(System.out, "");
		}
	}

	private void printConexao() throws IOException {

		StringBuilder sb = new StringBuilder("\n");
		String transacao = cenario.getNomeCaso();

		final String IP = conexao.getIp();
		final Integer porta = conexao.getPorta();
		final String channel = conexao.getChannel().getClass().getSimpleName();

		sb.append("IP:\t".toUpperCase());
		sb.append(IP).append("\n");
		sb.append("Porta:\t".toUpperCase());
		sb.append(porta).append("\n");
		sb.append("Channel: ".toUpperCase());
		sb.append(channel).append("\n");
		sb.append("Transação: ".toUpperCase());
		sb.append(transacao).append("\n");

		GravaLogs.gravar(sb, transacao);

		p.println(sb.toString());
	}

	public Cenario cenario() {
		return cenario;
	}

	public void setCenario(Cenario cenario) {
		this.cenario = cenario;
	}

	public Caso caso() {
		return caso;
	}

	public void setCaso(Caso caso) {
		this.caso = caso;
	}

	public Conexao conexao() {
		return conexao;
	}

	public void setConexao(Conexao conexao) {
		this.conexao = conexao;

		channel = conexao.getChannel();
	}

}
