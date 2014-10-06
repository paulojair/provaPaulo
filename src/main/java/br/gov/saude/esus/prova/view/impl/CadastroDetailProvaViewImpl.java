package br.gov.saude.esus.prova.view.impl;

import br.gov.saude.esus.prova.common.api.CadastroForm;
import br.gov.saude.esus.prova.common.api.exception.CampoValidacaoException;
import br.gov.saude.esus.prova.common.api.exception.GrupoValidacaoException;
import br.gov.saude.esus.prova.common.api.exception.ValidacaoException;
import br.gov.saude.esus.prova.presentation.api.detail.ex1.CadastroDetailProvaPresenter;
import br.gov.saude.esus.prova.presentation.api.detail.ex1.CadastroDetailProvaView;

import com.vaadin.server.AbstractErrorMessage.ContentMode;
import com.vaadin.server.ErrorMessage.ErrorLevel;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.UserError;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class CadastroDetailProvaViewImpl extends ProvaViewImpl<CadastroDetailProvaPresenter> implements CadastroDetailProvaView {

	private TextField fieldTitulo;
	private TextArea fieldDescricao;

	public CadastroDetailProvaViewImpl(CadastroDetailProvaPresenter presenter) {
		super(presenter);
	}

	@Override
	public void render() {
		this.addComponent(new Label("<h1>Cadastro Detail</h1>", com.vaadin.shared.ui.label.ContentMode.HTML));

		Panel panel = new Panel("Formulário");

		VerticalLayout content = new VerticalLayout();
		content.setSpacing(true);
		content.setMargin(true);

		content.addComponent(this.fieldTitulo = new TextField("Título"));
		content.addComponent(this.fieldDescricao = new TextArea("Descrição"));
		Button buttonSalvar = new Button("Salvar");

		buttonSalvar.setIcon(FontAwesome.CHECK);

		buttonSalvar.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				CadastroDetailProvaViewImpl.this.presenter.salvar();
			}
		});

		content.addComponent(buttonSalvar);

		panel.setContent(content);

		this.addComponent(panel);
	}

	@Override
	public CadastroForm fillOut() {
		CadastroForm form = new CadastroForm();
		form.setTitulo(this.fieldTitulo.getValue());
		form.setDescricao(this.fieldDescricao.getValue());
		return form;
	}

	@Override
	public void fillIn(CadastroForm form) {
		this.fieldTitulo.setValue(form.getTitulo());
		this.fieldDescricao.setValue(form.getDescricao());
	}

	@Override
	public void showValidacoes(ValidacaoException e){
		if(e instanceof GrupoValidacaoException){
			StringBuilder builder = new StringBuilder();
			GrupoValidacaoException grupo = (GrupoValidacaoException) e;
			for (ValidacaoException validacao : grupo.getValidacoes()) {
				if (validacao instanceof CampoValidacaoException) {
					CampoValidacaoException campo = (CampoValidacaoException) validacao;
					if (campo.getNomeCampo().equals("Título")) {
						this.setUserErrorFor(this.fieldTitulo, campo.getMessage());
					}
					builder.append(campo.getNomeCampo() + "\n");
				} else {
					builder.append(validacao.getMessage() + "\n");
				}
			}
			this.notifica("Campos preenchido de forma incorreta", builder.toString());
		} else {
			this.notifica("Campos preenchido de forma incorreta", e.getMessage());
		}
	}

	private void setUserErrorFor(AbstractComponent component, String message) {
		component.setComponentError(new UserError(message, ContentMode.TEXT, ErrorLevel.ERROR));
	}

}
