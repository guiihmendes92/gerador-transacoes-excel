package simulador.util;

import org.jpos.iso.ISOUtil;

public class ParseBit55 {

	public static String converteBit55(String bit55) {
		if (bit55 != null)
			return ISOUtil.hexString(bit55.getBytes());

		return null;
	}
	
	
	public static void main(String[] args) {
		final String bit55 = "9F2608A83DE947E9D6FEFF9F2701809F10200FA501A20300000000000000000000000F05930720900A4D441B4300044210109F360200789F02060000000130009F03060000000000009F1A020076950500808880005F2A0209869A031512239C01009F3704EF0D23195A0860429340000000725F3401008407A000000442101082025800";
		
		
		System.err.println(ParseBit55.converteBit55(bit55));
	}
}
