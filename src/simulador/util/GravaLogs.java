package simulador.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import simulador.transmissao.AutoGerar;

public class GravaLogs {
	private static String DIR = "C:\\Users\\guilherme.mendes\\Desenvolvimento\\Logs\\";
	public final static String CARTAO = AutoGerar.cartao.getPan();
	private static DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	private static Date date;

	static {
		DIR = novoDiretorio(AutoGerar.adquirente);
	}

	public static void gravar(StringBuilder sb, String tipoTransacao) throws IOException {
		date = new Date();

		File arquivo = new File(novoDiretorio(CARTAO) + df.format(date) + "_" + tipoTransacao + ".txt");

		if (!arquivo.exists()) {
			// cria um arquivo (vazio)
			arquivo.createNewFile();
		}

		// escreve no arquivo
		FileWriter fw = new FileWriter(arquivo, true);

		BufferedWriter bw = new BufferedWriter(fw);

		bw.write(sb.toString());
		bw.newLine();
		bw.close();
		fw.close();
	}

	public static void gravar(String mensagem, String tipoTransacao) throws IOException {
		date = new Date();

		File arquivo = new File(novoDiretorio(CARTAO) + df.format(date) + "_" + tipoTransacao + ".txt");

		if (!arquivo.exists()) {
			// cria um arquivo (vazio)
			arquivo.createNewFile();
		}

		// escreve no arquivo
		FileWriter fw = new FileWriter(arquivo, true);

		BufferedWriter bw = new BufferedWriter(fw);

		bw.write(mensagem);
		bw.newLine();
		bw.close();
		fw.close();
	}

	public static PrintStream geraLog(String tipoTransacao) {
		// ***********************
		// GERA LOG DAS TRANSAÇÕES
		// ***********************
		FileOutputStream fos = null;

		try {
			date = new Date();

			File file = new File(novoDiretorio(CARTAO) + df.format(date) + "_" + tipoTransacao + ".txt");

			fos = new FileOutputStream(file, true);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return new PrintStream(fos);
	}

	public static String novoDiretorio(String nomeDiretorio) {
		String separador = java.io.File.separator;

		final String novoDIR = DIR + nomeDiretorio;

		try {

			if (!Paths.get(novoDIR).toFile().exists()) { // Verifica
				new File(novoDIR).mkdir(); // Cria o diretório

			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Erro", "Erro ao criar o diretório" + ex.toString(),
					JOptionPane.ERROR_MESSAGE);
		}
		return novoDIR + separador;
	}
}
