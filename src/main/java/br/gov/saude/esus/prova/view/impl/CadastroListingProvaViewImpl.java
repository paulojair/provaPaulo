package br.gov.saude.esus.prova.view.impl;

import java.util.List;

import br.gov.saude.esus.prova.common.api.CadastroForm;
import br.gov.saude.esus.prova.presentation.api.listing.ex1.CadastroListingProvaPresenter;
import br.gov.saude.esus.prova.presentation.api.listing.ex1.CadastroListingProvaView;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;

public class CadastroListingProvaViewImpl extends ProvaViewImpl<CadastroListingProvaPresenter> implements CadastroListingProvaView, ColumnGenerator {

	private Table table;
	private BeanItemContainer<CadastroForm> beanItemContainer;

	public CadastroListingProvaViewImpl(CadastroListingProvaPresenter presenter) {
		super(presenter);
	}

	@Override
	public void render() {
		this.addComponent(new Label("<h1>Cadastro Listing</h1>", ContentMode.HTML));

		Panel panel = new Panel("Formulário");

		VerticalLayout content = new VerticalLayout();
		content.setSizeFull();
		content.setSpacing(true);
		content.setMargin(true);

		this.table = new Table();
		this.table.setSizeFull();
		this.table.setHeight(500, Unit.PIXELS);
		this.table.setImmediate(true);
		this.beanItemContainer = new BeanItemContainer<CadastroForm>(CadastroForm.class);
		this.table.setContainerDataSource(this.beanItemContainer);
		this.table.setVisibleColumns("id", "titulo");
		this.table.setColumnHeaders("Id", "Título");
		this.table.addGeneratedColumn("Ações", this);
		content.addComponent(this.table);

		Button buttonAdicionar = new Button("Adicionar");

		buttonAdicionar.setIcon(FontAwesome.PLUS);

		buttonAdicionar.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				CadastroListingProvaViewImpl.this.presenter.adicionar();
			}
		});

		content.addComponent(buttonAdicionar);

		panel.setContent(content);

		this.addComponent(panel);
	}

	@Override
	public void setListingItems(List<CadastroForm> items) {
		this.beanItemContainer.removeAllItems();
		if (!items.isEmpty()) {
			this.beanItemContainer.addAll(items);
		}
	}

	@Override
	public Object generateCell(Table source, final Object itemId, Object columnId) {
		HorizontalLayout cell = new HorizontalLayout();
		cell.setSpacing(true);

		Button buttonEditar = new Button(FontAwesome.EDIT);

		buttonEditar.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				CadastroListingProvaViewImpl.this.presenter.editar(CadastroListingProvaViewImpl.this.beanItemContainer.getItem(itemId).getBean().getId());
			}
		});

		cell.addComponent(buttonEditar);

		Button buttonExcluir = new Button(FontAwesome.TIMES);

		buttonExcluir.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				CadastroListingProvaViewImpl.this.presenter.remover(CadastroListingProvaViewImpl.this.beanItemContainer.getItem(itemId).getBean().getId());
			}
		});

		cell.addComponent(buttonExcluir);

		return cell;
	}

}
