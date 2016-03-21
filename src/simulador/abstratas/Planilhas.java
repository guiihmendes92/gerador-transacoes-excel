package simulador.abstratas;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import simulador.constantes.NomePlanilha;
import simulador.interfaces.Planilha;

public abstract class Planilhas implements Planilha, NomePlanilha {

	protected Workbook workBook;
	protected Iterator<Row> linhas;
	protected Row linha;
	protected Iterator<Cell> colunas;
	protected Cell coluna;
	protected Sheet planilha;
	protected String localArquivo;

	public Planilhas(String localArquivo) {
		this.localArquivo = localArquivo;
		lerArquivo();
	}

	protected void setSheet(String nomePlanilha) {
		planilha = workBook.getSheet(nomePlanilha);
		linhas = planilha.rowIterator();
	}

	private void lerArquivo() {

		FileInputStream leituraArquivo;

		try {
			leituraArquivo = new FileInputStream(new File(localArquivo));

			setWorkbook(leituraArquivo);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setWorkbook(FileInputStream leituraArquivo) throws IOException {

		if (localArquivo.endsWith("xlsx")) {
			workBook = new XSSFWorkbook(leituraArquivo);
		} else if (localArquivo.endsWith("xls")) {
			workBook = new HSSFWorkbook(leituraArquivo);
		} else {
			throw new IllegalArgumentException("The specified file is not Excel file");
		}

	}

}
