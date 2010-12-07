package com.github.jskeys;

import com.vaadin.Application;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Layout.MarginInfo;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class Main extends Application {
	private static final long serialVersionUID = -5312231874375635569L;

	@Override
	public void init() {

		final Window window = new Window("Editor");

		VerticalLayout vertical = new VerticalLayout();
		vertical.setHeight(Sizeable.SIZE_UNDEFINED, 0);
		vertical.setWidth("100%");
		vertical.setMargin(new MarginInfo(true, false, true, false));
		window.setSizeFull();

		RichTextArea rta = new RichTextArea();
		rta.setWidth("720px");
		vertical.addComponent(rta);
		vertical.setComponentAlignment(rta, Alignment.TOP_CENTER);
		vertical.setExpandRatio(rta, 1f);

		window.addComponent(vertical);
		this.setMainWindow(window);
	}
}
