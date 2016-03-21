package simulador.abstratas;

import java.io.IOException;
import java.util.Date;

import org.jpos.iso.BaseChannel;
import org.jpos.iso.ISODate;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

import simulador.excel.padrao.Caso;
import simulador.interfaces.Pedido;
import simulador.util.GravaLogs;

public abstract class Transmitir implements Pedido {
	protected ISOMsg pedido;
	protected ISOMsg resposta;
	protected BaseChannel channel;
	protected Caso caso;

	public Transmitir(Caso caso, BaseChannel channel) {
		super();
		this.caso = caso;
		this.channel = channel;
	}

	private synchronized void conectar() {

		try {

			if (!channel.isConnected()) {
				channel.connect();

			}

			if (channel.isConnected()) {

			}

		} catch (Exception e) {

			// printConexao();
			System.err.println(e.getMessage());//
		}
	}

	public void dump(ISOMsg mensagem, String tipoTransacao) {

		try {

			if (!mensagem.getChildren().isEmpty()) {

				StringBuilder sb = new StringBuilder("\n");

				sb.append(getFormatDate()).append("\n");
				sb.append(new String(mensagem.pack()));
				System.out.println(sb.toString());

				GravaLogs.gravar(sb, tipoTransacao);
				mensagem.dump(GravaLogs.geraLog(tipoTransacao), "");
				mensagem.dump(System.out, "");
			}
		} catch (ISOException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getFormatDate() {
		return ISODate.formatDate(new Date(), "dd/MM/yyyy HH:mm:ss,SSS");
	}
}
