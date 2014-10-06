package br.gov.saude.esus.prova.common.api.exception;

import java.util.List;

public class GrupoValidacaoException extends ValidacaoException {

	private List<ValidacaoException> validacoes;

	public GrupoValidacaoException(List<ValidacaoException> validacoes) {
		super("");
		this.validacoes = validacoes;
	}

	public List<ValidacaoException> getValidacoes() {
		return this.validacoes;
	}

}
