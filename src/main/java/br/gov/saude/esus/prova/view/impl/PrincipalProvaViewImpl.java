package br.gov.saude.esus.prova.view.impl;

import br.gov.saude.esus.prova.presentation.api.ProvaPresenter;
import br.gov.saude.esus.prova.presentation.api.principal.PrincipalProvaView;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class PrincipalProvaViewImpl extends ProvaViewImpl<ProvaPresenter> implements PrincipalProvaView {

	public PrincipalProvaViewImpl(ProvaPresenter presenter) {
		super(presenter);
	}

	@Override
	public void render() {
		this.setSpacing(true);

		this.addComponent(new Label("<h1>Conteúdo Principal</h1>", ContentMode.HTML));

		Panel panel = new Panel("Questões da prova");

		VerticalLayout content = new VerticalLayout();
		content.setMargin(true);
		content.setSpacing(true);

		content.addComponent(new Label("No dia da prova as questões estarão descritas aqui.",
				ContentMode.HTML));

		panel.setContent(content);

		this.addComponent(panel);
	}

}
