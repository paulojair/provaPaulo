package br.gov.saude.esus.prova.presentation.api.detail;

import br.gov.saude.esus.prova.common.api.exception.ValidacaoException;
import br.gov.saude.esus.prova.presentation.api.ProvaView;

public interface DetailProvaView<FORM> extends ProvaView {

	public FORM fillOut();

	public void fillIn(FORM form);

	void showValidacoes(ValidacaoException e);

}
