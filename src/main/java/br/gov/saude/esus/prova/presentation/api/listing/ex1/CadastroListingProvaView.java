package br.gov.saude.esus.prova.presentation.api.listing.ex1;

import java.util.List;

import br.gov.saude.esus.prova.common.api.CadastroForm;
import br.gov.saude.esus.prova.presentation.api.listing.ListingProvaView;

public interface CadastroListingProvaView extends ListingProvaView {

	void setListingItems(List<CadastroForm> items);

}
