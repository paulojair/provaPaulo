package br.gov.saude.esus.prova.business.api;

import java.util.List;

import br.gov.saude.esus.prova.common.api.CadastroForm;
import br.gov.saude.esus.prova.common.api.exception.ValidacaoException;

public interface CadastroService {

	void salvar(CadastroForm form) throws ValidacaoException;

	List<CadastroForm> listAll();
	
	List<CadastroForm> listSearched(String titleToSearch);

	void remover(Long idToRemove);

	CadastroForm load(Long idToLoad);

}
