package simulador.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Sequencer {
	// private static final String ROOT = "Z:\\Sequences\\";
	private static final String ROOT = "C:\\Users\\guilherme.mendes\\Desenvolvimento\\eclipse-integracao-continua\\workspace\\log\\sequence";
	private File file;
	private int seq;

	private Sequencer(String sequenceName) {
		initializeRoot();
		file = new File(ROOT + sequenceName);
	}

	public static Sequencer compile(String sequenceName) {
		Sequencer sequence = new Sequencer(sequenceName);
		sequence.initialize();
		return sequence;
	}

	private void initializeRoot() {
		File rootDir = new File(ROOT);
		if (!rootDir.exists())
			if (!rootDir.mkdir())
				throw new ExceptionInInitializerError(
						"N�o foi poss�vel criar o diret�rio Raiz.");
	}

	private synchronized void initialize() {
		if (!file.exists()) {
			seq = 0;
			return;
		}

		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			seq = Integer.parseInt(br.readLine());

		} catch (IOException ex) {
			throw new ExceptionInInitializerError(ex);
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (Exception ex) {
				throw new ExceptionInInitializerError(ex);
			}
		}

	}

	public synchronized int nextInt() {
		initialize();
		int nextNumber = ++seq;
		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			bw.write(nextNumber + "");

		} catch (IOException ex) {
			throw new ExceptionInInitializerError(ex);
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (Exception ex) {
				throw new ExceptionInInitializerError(ex);
			}
		}

		return nextNumber;
	}

	public static void main(String[] args) throws Exception {
		Sequencer seq = Sequencer.compile("teste.seq");

		while (true)
			System.out.println(seq.nextInt());
	}

}