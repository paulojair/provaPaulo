package br.gov.saude.esus;

import javax.servlet.annotation.WebServlet;

import br.gov.saude.esus.prova.common.api.ServiceInjector;
import br.gov.saude.esus.prova.presentation.api.ProvaPresenter;
import br.gov.saude.esus.prova.presentation.api.ProvaView;
import br.gov.saude.esus.prova.presenter.impl.CadastroListingProvaPresenterImpl;
import br.gov.saude.esus.prova.presenter.impl.PrincipalProvaPresenterImpl;
import br.gov.saude.esus.prova.view.impl.ui.ContentManager;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Component;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
@SuppressWarnings("serial")
public class ProvaUI extends UI {

	private ContentManager contentManager;

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = ProvaUI.class, widgetset = "AppWidgetSet")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout rootContent = new VerticalLayout();
		rootContent.setMargin(true);
		rootContent.setSpacing(true);
		this.setContent(rootContent);

		MenuBar menuBar = new MenuBar();

		menuBar.addItem("Principal", new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				ProvaUI.this.showPrincipal();
			}
		});

		menuBar.addItem("Exemplo Listing", new Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				ProvaUI.this.handlePresenter(new CadastroListingProvaPresenterImpl(ProvaUI.this));
			}
		});

		rootContent.addComponent(menuBar);

		this.contentManager = new ContentManagerImpl(rootContent);

		this.showPrincipal();
	}

	private void showPrincipal() {
		this.handlePresenter(new PrincipalProvaPresenterImpl(this));
	}

	public void handlePresenter(ProvaPresenter presenter) {
		ServiceInjector.getInstance().inject(presenter);
		presenter.renderView(this.contentManager);
	}

	private class ContentManagerImpl implements ContentManager {

		private final Layout rootContent;
		private Component content;

		public ContentManagerImpl(Layout rootContent) {
			super();
			this.rootContent = rootContent;
		}


		@Override
		public void setContentView(ProvaView view) {
			if (this.content != null) {
				this.rootContent.replaceComponent(this.content, (Component) view);
			} else {
				this.rootContent.addComponent((Component) view);
			}
			this.content = (Component) view;
		}

	}

}
