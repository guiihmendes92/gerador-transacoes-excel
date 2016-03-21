package simulador.excel.padrao;

import java.io.PrintStream;

import org.jpos.iso.ISOMsg;

import simulador.abstratas.ExcelPadrao;
import simulador.excel.getnet.PlanilhaTransacoesGetnet;
import simulador.util.Cronometro;

public class PlanilhaTransacoesTestes {

	private static int i;

	public static void main(String[] args) {

		try {

			final String URL = "D:\\Users\\GUIIH\\Downloads\\";
			String arquivo = "casos-teste-getnet.xlsx";
			String localArquivo = URL + arquivo;
			Cronometro.start();
			ExcelPadrao pp = null;

			// pp = new PlanilhaTransacoesCabal(localArquivo);
			// pp.ler();

			// gerarIso(pp.transacoes);

			arquivo = "casos-teste-getnet.xlsx";
			localArquivo = URL + arquivo;

			pp = new PlanilhaTransacoesGetnet(localArquivo);
			pp.ler();

			gerarIso(pp.transacoes);

		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}

	}

	private static void gerarIso(Transacoes transacoes) {

		PrintStream p = System.out;

		transacoes.getCenarios().forEach(cenario -> cenario.getCasos().forEach(caso -> {

			ISOMsg autorizacao = caso.getAutorizacao();
			ISOMsg desfazimento = caso.getDesfazimento();
			ISOMsg estorno = caso.getEstorno();

			p.println(autorizacao);
			p.println(desfazimento);
			p.println(estorno);

			// desfazimento.dump(p, "");
			// msgAutorizacao.dump(p, "");

			// autorizacao.dump(p, "A");
			// desfazimento.dump(p, "D");
			// estorno.dump(p, "E");
			i++;
			p.println();
		}));

		p.println(i + " repetiu\n");
		Cronometro.stop();

		Cronometro.exibeTempo("leitura".toUpperCase());
	}

}
