package simulador.util;

public class Padder {
	private static boolean isNull( String str ) {
		return str == null;
	}
	
	public static String leftPad( String str, int tam, char ch ) {
		if( isNull( str ) )
			return null;
		int size = str.length() - tam;
		if( size >= 0 )
			return str;
		return String.format( "%" + size + "s", "" ).replace( " ", ch+"" ) + str;
	}
	
	public static String rightPad( String str, int tam, char ch ) {
		if( isNull( str ) )
			return null;
		int size = str.length() - tam;
		if( size >= 0 )
			return str;
		return str + String.format( "%" + size + "s", "" ).replace( " ", ch+"" );
	}

	public static void main( String[] args ) {
		System.out.println( "Left Padder: " + leftPad( "str", 10, 'x' ) );
		System.out.println( "Right Padder: " + rightPad( "str", 10, 'x' ) );
	}
}