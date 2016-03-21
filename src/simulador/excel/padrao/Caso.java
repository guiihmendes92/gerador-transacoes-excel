package simulador.excel.padrao;

import org.jpos.iso.ISOMsg;

public class Caso {

	private ISOMsg autorizacao = new ISOMsg();
	private ISOMsg respostaAutorizacao = new ISOMsg();
	private ISOMsg estorno = new ISOMsg();
	private ISOMsg respostaEstorno = new ISOMsg();
	private ISOMsg desfazimento = new ISOMsg();
	private ISOMsg respostaDesfazimento = new ISOMsg();
	private ISOMsg confirmaAutorizacao = new ISOMsg();
	private ISOMsg confirmaEstorno = new ISOMsg();
	private String nomeCaso;
	private boolean cancelar;
	private boolean desfazer;
	private boolean confirmarAutorizacao;
	private boolean confirmarEstorno;

	public boolean isConfirmarAutorizacao() {
		return confirmarAutorizacao;
	}

	public void setConfirmarAutorizacao(boolean confirmarAutorizacao) {
		this.confirmarAutorizacao = confirmarAutorizacao;
	}

	public boolean isConfirmarEstorno() {
		return confirmarEstorno;
	}

	public void setConfirmarEstorno(boolean confirmarEstorno) {
		this.confirmarEstorno = confirmarEstorno;
	}

	public boolean isCancelar() {
		return cancelar;
	}

	public void setCancelar(boolean cancelar) {
		this.cancelar = cancelar;
	}

	public boolean isDesfazer() {
		return desfazer;
	}

	public void setDesfazer(boolean desfazer) {
		this.desfazer = desfazer;
	}

	public ISOMsg getConfirmaAutorizacao() {
		return confirmaAutorizacao;
	}

	public void setConfirmaAutorizacao(ISOMsg confirmaAutorizacao) {
		this.confirmaAutorizacao = confirmaAutorizacao;
	}

	public ISOMsg getConfirmaEstorno() {
		return confirmaEstorno;
	}

	public void setConfirmaEstorno(ISOMsg confirmaEstorno) {
		this.confirmaEstorno = confirmaEstorno;
	}

	public ISOMsg getAutorizacao() {
		return autorizacao;
	}

	public void setAutorizacao(ISOMsg autorizacao) {
		this.autorizacao = autorizacao;
	}

	public ISOMsg getRespostaAutorizacao() {
		return respostaAutorizacao;
	}

	public void setRespostaAutorizacao(ISOMsg respostaAutorizacao) {
		this.respostaAutorizacao = respostaAutorizacao;
	}

	public ISOMsg getEstorno() {
		return estorno;
	}

	public void setEstorno(ISOMsg estorno) {
		this.estorno = estorno;
	}

	public ISOMsg getRespostaEstorno() {
		return respostaEstorno;
	}

	public void setRespostaEstorno(ISOMsg respostaEstorno) {
		this.respostaEstorno = respostaEstorno;
	}

	public ISOMsg getDesfazimento() {
		return desfazimento;
	}

	public void setDesfazimento(ISOMsg desfazimento) {
		this.desfazimento = desfazimento;
	}

	public ISOMsg getRespostaDesfazimento() {
		return respostaDesfazimento;
	}

	public void setRespostaDesfazimento(ISOMsg respostaDesfazimento) {
		this.respostaDesfazimento = respostaDesfazimento;
	}

	public String getNomeCaso() {
		return nomeCaso;
	}

	public void setNomeCaso(String nomeCaso) {
		this.nomeCaso = nomeCaso;
	}

}
