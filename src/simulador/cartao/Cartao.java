package simulador.cartao;

import simulador.transmissao.AutoGerar;

public class Cartao {
	private String pan;
	private String track1;
	private String track2;
	private String password;
	private String cvv1;
	private String cvv2;
	private String positiveIdentity;
	private String bin;
	private String binExtended;
	private String expirationDate;
	private String nome;
	private String cardHolder;
	private String serviceCode;

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getPan() {
		if (AutoGerar.isNull(pan)) {
			return "";
		}

		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getTrack1() {
		return track1;
	}

	public void setTrack1(String track1) {
		this.track1 = track1;
	}

	public String getTrack2() {
		return track2;
	}

	public void setTrack2(String track2) {
		this.track2 = track2;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCvv1() {
		if (AutoGerar.isNull(cvv1)) {
			return "";
		}
		return cvv1;
	}

	public void setCvv1(String cvv1) {
		this.cvv1 = cvv1;
	}

	public String getCvv2() {
		if (AutoGerar.isNull(cvv2)) {
			return "";
		}
		return cvv2;
	}

	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}

	public String getPositiveIdentity() {
		if (AutoGerar.isNull(positiveIdentity))
			return "";
		return positiveIdentity;
	}

	public void setPositiveIdentity(String positiveIdentity) {
		this.positiveIdentity = positiveIdentity;
	}

	public String getBin() {
		return bin;
	}

	public void setBin(String bin) {
		this.bin = bin;
	}

	public String getBinExtended() {
		return binExtended;
	}

	public void setBinExtended(String binExtended) {
		this.binExtended = binExtended;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCardHolder() {
		if (AutoGerar.isNull(cardHolder)) {
			return "";
		}
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

}
