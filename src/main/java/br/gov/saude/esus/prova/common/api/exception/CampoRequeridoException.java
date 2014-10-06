package br.gov.saude.esus.prova.common.api.exception;

public class CampoRequeridoException extends CampoValidacaoException {

	public CampoRequeridoException(String nomeCampo) {
		super(nomeCampo, "Este campo é de preenchimento obrigatório");
	}

}
