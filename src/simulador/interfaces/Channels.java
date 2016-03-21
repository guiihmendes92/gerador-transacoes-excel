package simulador.interfaces;

import org.jpos.iso.BaseChannel;
import org.jpos.iso.channel.ASCIIChannel;
import org.jpos.iso.channel.NACChannel;
import org.jpos.iso.channel.PADChannel;

import simulador.transmissao.CieloChannel;

public interface Channels {

	public static BaseChannel NACChannel = new NACChannel();
	public static BaseChannel PADChannel = new PADChannel();
	public static BaseChannel CieloChannel = new CieloChannel();
	public static BaseChannel ASCIIChannel = new ASCIIChannel();

}
