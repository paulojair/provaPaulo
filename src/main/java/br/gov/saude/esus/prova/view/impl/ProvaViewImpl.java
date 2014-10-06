package br.gov.saude.esus.prova.view.impl;

import br.gov.saude.esus.prova.presentation.api.ProvaPresenter;
import br.gov.saude.esus.prova.presentation.api.ProvaView;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;

public abstract class ProvaViewImpl<P extends ProvaPresenter> extends VerticalLayout implements ProvaView {

	protected final P presenter;

	public ProvaViewImpl(P presenter) {
		super();
		this.presenter = presenter;
	}

	@Override
	public void notifica(String titulo, String mensagem) {
		Notification.show(titulo, mensagem, Type.TRAY_NOTIFICATION);
	}

}
