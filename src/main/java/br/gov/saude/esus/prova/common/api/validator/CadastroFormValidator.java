package br.gov.saude.esus.prova.common.api.validator;

import java.util.ArrayList;
import java.util.List;

import br.gov.saude.esus.prova.common.api.CadastroForm;
import br.gov.saude.esus.prova.common.api.exception.CampoRequeridoException;
import br.gov.saude.esus.prova.common.api.exception.GrupoValidacaoException;
import br.gov.saude.esus.prova.common.api.exception.ValidacaoException;

public class CadastroFormValidator {

	public void checkForm(CadastroForm form) throws ValidacaoException {
		List<ValidacaoException> validacoes = new ArrayList<ValidacaoException>();

		try {
			this.checkTitulo(form);
		} catch (ValidacaoException ex) {
			validacoes.add(ex);
		}

		if (!validacoes.isEmpty()) {
			throw new GrupoValidacaoException(validacoes);
		}
	}

	private void checkTitulo(CadastroForm form) throws ValidacaoException {
		if(form.getTitulo() == null || form.getTitulo().trim().isEmpty()){
			throw new CampoRequeridoException("Título");
		}
	}

}
