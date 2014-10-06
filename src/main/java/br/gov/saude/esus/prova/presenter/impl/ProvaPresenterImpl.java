package br.gov.saude.esus.prova.presenter.impl;

import br.gov.saude.esus.ProvaUI;
import br.gov.saude.esus.prova.presentation.api.ProvaPresenter;
import br.gov.saude.esus.prova.presentation.api.ProvaView;
import br.gov.saude.esus.prova.view.impl.ui.ContentManager;

public abstract class ProvaPresenterImpl<V extends ProvaView> implements ProvaPresenter {

	protected V view;
	protected ProvaUI ui;

	public ProvaPresenterImpl(ProvaUI ui) {
		super();
		this.ui = ui;
	}

	@Override
	public void renderView(ContentManager contentManager) {
		this.view = this.createView();
		this.view.render();
		contentManager.setContentView(this.view);
	}

	protected abstract V createView();

}
