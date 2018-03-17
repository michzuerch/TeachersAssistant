package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.slf4j.LoggerFactory;
import org.vaadin.teemusa.flexlayout.FlexLayout;

import javax.inject.Inject;

/**
 * Created by michzuerch on 09.05.17.
 */
@Theme("anouman")
@Title("Anouman")
@Push(PushMode.AUTOMATIC)
@PreserveOnRefresh
@SuppressWarnings("serial")
@CDIUI("")
public class LehrerVerwaltungUI extends UI {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(LehrerVerwaltungUI.class.getName());

    @Inject
    private CDIViewProvider cdiViewProvider;

    @Override
    protected void init(VaadinRequest request) {
        Panel container = new Panel();

        Navigator navigator = new Navigator(this, container);
        navigator.addProvider(cdiViewProvider);
        FlexLayout naviBar = FlexLayout.create().horizontal().justifyContent()
                .spaceAround().alignContent().end().nowrap().build();
        naviBar.addComponents(new Menu());
        naviBar.setHeight("45px");

        VerticalLayout content = new VerticalLayout(naviBar, container);
        setContent(content);
        navigator.navigateTo("AboutView");
        setSizeFull();
        content.setSizeFull();
        container.setSizeFull();
        content.setExpandRatio(container, 1.0f);
    }
}