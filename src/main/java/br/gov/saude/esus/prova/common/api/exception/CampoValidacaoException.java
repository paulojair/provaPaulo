package br.gov.saude.esus.prova.common.api.exception;

public class CampoValidacaoException extends ValidacaoException {

	private String nomeCampo;

	public CampoValidacaoException(String nomeCampo, String mensagem) {
		super(mensagem);
		this.nomeCampo = nomeCampo;
	}

	public String getNomeCampo() {
		return this.nomeCampo;
	}

}
