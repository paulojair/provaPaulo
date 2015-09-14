package br.gov.saude.esus.prova.presentation.api.listing;

import br.gov.saude.esus.prova.presentation.api.ProvaPresenter;

public interface ListingProvaPresenter extends ProvaPresenter {

	void remover(Long idToRemove);

	void editar(Long idToEdit);

	void adicionar();
	
	void buscar(String titleToSearch);

}
