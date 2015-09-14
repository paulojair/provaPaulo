package br.gov.saude.esus.prova.business.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.gov.saude.esus.prova.business.api.CadastroService;
import br.gov.saude.esus.prova.common.api.CadastroForm;
import br.gov.saude.esus.prova.common.api.exception.ValidacaoException;
import br.gov.saude.esus.prova.common.api.validator.CadastroFormValidator;
import br.gov.saude.esus.prova.persistence.CadastroEntity;
import br.gov.saude.esus.prova.persistence.QCadastroEntity;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Projections;

@Stateless
public class CadastroServiceImpl implements CadastroService {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void salvar(CadastroForm form) throws ValidacaoException {
		new CadastroFormValidator().checkForm(form);

		CadastroEntity entity = null;

		if (form.getId() != null) {
			entity = this.em.find(CadastroEntity.class, form.getId());
		} else {
			entity = new CadastroEntity();
		}

		entity.setTitulo(form.getTitulo());
		entity.setAutor(form.getAutor());
		entity.setDescricao(form.getDescricao());

		this.em.persist(entity);
	}

	@Override
	public CadastroForm load(Long idToLoad) {
		CadastroEntity entity = this.em.find(CadastroEntity.class, idToLoad);

		CadastroForm form = new CadastroForm();

		form.setId(idToLoad);
		form.setTitulo(entity.getTitulo());
		form.setAutor(entity.getAutor());
		form.setDescricao(entity.getDescricao());

		return form;
	}

	@Override
	public void remover(Long idToRemove) {
		this.em.remove(this.em.getReference(CadastroEntity.class, idToRemove));
	}

	@Override
	public List<CadastroForm> listAll(){
		QCadastroEntity alias = QCadastroEntity.cadastroEntity;
		return new JPAQuery(this.em).from(alias).list(Projections.bean(CadastroForm.class, alias.id.as("id"), alias.titulo.as("titulo"),alias.autor.as("autor"), alias.descricao.as("descricao")));
	}
	
	public List<CadastroForm> listSearched(String titleToSearch){
		QCadastroEntity alias = QCadastroEntity.cadastroEntity;
		return new JPAQuery(this.em).from(alias).where(alias.titulo.eq(titleToSearch)).list(Projections.bean(CadastroForm.class, alias.id.as("id"), alias.titulo.as("titulo"),alias.autor.as("autor"), alias.descricao.as("descricao")));
	}

}
