package br.gov.saude.esus.prova.presenter.impl;

import br.gov.saude.esus.ProvaUI;
import br.gov.saude.esus.prova.presentation.api.principal.PrincipalProvaPresenter;
import br.gov.saude.esus.prova.presentation.api.principal.PrincipalProvaView;
import br.gov.saude.esus.prova.view.impl.PrincipalProvaViewImpl;

public class PrincipalProvaPresenterImpl extends ProvaPresenterImpl<PrincipalProvaView> implements PrincipalProvaPresenter {

	public PrincipalProvaPresenterImpl(ProvaUI ui) {
		super(ui);
	}

	@Override
	protected PrincipalProvaView createView() {
		return new PrincipalProvaViewImpl(this);
	}

}
