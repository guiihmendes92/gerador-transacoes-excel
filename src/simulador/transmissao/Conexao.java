package simulador.transmissao;

import java.net.SocketException;

import org.jpos.iso.BaseChannel;
import org.jpos.iso.ISOException;
import org.jpos.iso.channel.PostChannel;
import org.jpos.iso.packager.GenericPackager;

public class Conexao {

	private String ip;
	private int porta;
	private BaseChannel channel;
	private static int timeout;

	public Conexao(String ip, int porta, BaseChannel channel) {
		this.ip = ip;
		this.porta = porta;
		this.channel = channel;
		// this.channelEnum = channel;
	}

	/**
	 * 
	 */
	public Conexao() {
		super();

	}

	public void setchannel() {
		try {
			channel.setHost(getIp(), getPorta());
			channel.setPackager(getPackager());
			channel.setTimeout(getTimeout());

		} catch (ISOException | SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getTimeout() {
		if (Conexao.timeout == 0)
			Conexao.timeout = 30000;

		return Conexao.timeout;
	}

	public GenericPackager getPackager() throws ISOException {

		if (channel instanceof CieloChannel)
			return new GenericPackager("src/main/resources/cielo.xml");

		if (channel instanceof PostChannel)
			return new GenericPackager(
					"src/main/resources/iso87ebcdic_master.xml");

		return new GenericPackager("src/main/resources/iso87ascii.xml");

	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPorta() {
		return porta;
	}

	public void setPorta(int porta) {
		this.porta = porta;
	}

	public BaseChannel getChannel() {
		return channel;
	}

	public void setChannel(BaseChannel channel) {
		this.channel = channel;
	}

	public static void setTimeout(int timeout) {
		Conexao.timeout = timeout;
	}

}
