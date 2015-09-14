package br.gov.saude.esus.prova.presenter.impl;

import javax.ejb.EJB;

import br.gov.saude.esus.ProvaUI;
import br.gov.saude.esus.prova.business.api.CadastroService;
import br.gov.saude.esus.prova.presentation.api.listing.ex1.CadastroListingProvaPresenter;
import br.gov.saude.esus.prova.presentation.api.listing.ex1.CadastroListingProvaView;
import br.gov.saude.esus.prova.view.impl.CadastroListingProvaViewImpl;
import br.gov.saude.esus.prova.view.impl.ui.ContentManager;

public class CadastroListingProvaPresenterImpl extends ProvaPresenterImpl<CadastroListingProvaView> implements CadastroListingProvaPresenter {

	@EJB
	private CadastroService cadastroService;

	public CadastroListingProvaPresenterImpl(ProvaUI ui) {
		super(ui);
	}

	@Override
	public void renderView(ContentManager contentManager) {
		super.renderView(contentManager);

		this.refresh();
	}

	public void refresh() {
		this.view.setListingItems(this.cadastroService.listAll());
	}

	@Override
	protected CadastroListingProvaView createView() {
		return new CadastroListingProvaViewImpl(this);
	}

	@Override
	public void adicionar() {
		this.ui.handlePresenter(new CadastroDetailProvaPresenterImpl(this.ui));
	}

	@Override
	public void editar(Long idToEdit) {
		CadastroDetailProvaPresenterImpl detailPresenter = new CadastroDetailProvaPresenterImpl(this.ui);
		this.ui.handlePresenter(detailPresenter);
		detailPresenter.editar(idToEdit);
	}

	@Override
	public void remover(Long idToRemove) {
		this.cadastroService.remover(idToRemove);
		this.refresh();
	}

	@Override
	public void buscar(String titleToSearch) {
		if((titleToSearch == null) || titleToSearch.trim().isEmpty()){
			this.refresh();
		} else{
			this.view.setListingItems(this.cadastroService.listSearched(titleToSearch));
		}
	}

}
