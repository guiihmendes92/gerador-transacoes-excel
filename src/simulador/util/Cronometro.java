package simulador.util;

public class Cronometro {
	private static long tempoInicial;
	private static long tempoFinal;
	private static long diferencaDeTempo;

	/**
	 * Inicia a contagem temporal
	 */
	public static synchronized void start() {
		tempoInicial = System.currentTimeMillis();
		tempoFinal = 0;
	}

	/**
	 * Calcula a diferença temporal
	 * 
	 * @return
	 */
	public static synchronized void stop() {
		tempoFinal = System.currentTimeMillis();
		diferencaDeTempo = tempoFinal - tempoInicial;
	}

	/**
	 * Retorna o diferença de tempo medida
	 * 
	 * @return
	 * 
	 * @return tempo em segundos
	 */
	public static synchronized void exibeTempo() {
		System.err.printf("%.3f segundos%n", diferencaDeTempo / 1000d);

	}

	public static synchronized void exibeTempo(String msg) {
		System.err.printf(msg + "\t" + "%.3f segundos%n",
				diferencaDeTempo / 1000d);

	}
}
