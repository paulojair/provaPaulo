package br.gov.saude.esus.prova.presenter.impl;

import javax.ejb.EJB;

import br.gov.saude.esus.ProvaUI;
import br.gov.saude.esus.prova.business.api.CadastroService;
import br.gov.saude.esus.prova.common.api.exception.ValidacaoException;
import br.gov.saude.esus.prova.presentation.api.detail.ex1.CadastroDetailProvaPresenter;
import br.gov.saude.esus.prova.presentation.api.detail.ex1.CadastroDetailProvaView;
import br.gov.saude.esus.prova.view.impl.CadastroDetailProvaViewImpl;

public class CadastroDetailProvaPresenterImpl extends ProvaPresenterImpl<CadastroDetailProvaView> implements CadastroDetailProvaPresenter {

	@EJB
	private CadastroService cadastroService;

	public CadastroDetailProvaPresenterImpl(ProvaUI ui) {
		super(ui);
	}

	@Override
	protected CadastroDetailProvaView createView() {
		return new CadastroDetailProvaViewImpl(this);
	}

	public void editar(Long idToEdit) {
		this.view.fillIn(this.cadastroService.load(idToEdit));
	}

	@Override
	public void salvar() {
		try {
			this.cadastroService.salvar(this.view.fillOut());
			this.view.notifica("Sucesso!", "registro salvo");
			this.ui.handlePresenter(new CadastroListingProvaPresenterImpl(this.ui));
		} catch (ValidacaoException e) {
			this.view.showValidacoes(e);
		}
	}
	
	public void cancelar() {
		this.view.notifica("Sucesso!", "nada foi alterado");
		this.ui.handlePresenter(new CadastroListingProvaPresenterImpl(this.ui));
	}

}
