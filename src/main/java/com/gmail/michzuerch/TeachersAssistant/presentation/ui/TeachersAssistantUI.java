package com.gmail.michzuerch.TeachersAssistant.presentation.ui;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.slf4j.LoggerFactory;
import org.vaadin.teemusa.flexlayout.FlexLayout;

import javax.inject.Inject;

@Theme("teachersassistant")
@Title("Teachers Assistant 0.1")
@Push(PushMode.AUTOMATIC)
@PreserveOnRefresh
@SuppressWarnings("serial")
@CDIUI("")
public class TeachersAssistantUI extends UI {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(TeachersAssistantUI.class.getName());

    @Inject
    private CDIViewProvider cdiViewProvider;

    @Override
    protected void init(VaadinRequest request) {
        HorizontalLayout container = new HorizontalLayout();

        Navigator navigator = new Navigator(this, container);
        navigator.addProvider(cdiViewProvider);
        FlexLayout naviBar = FlexLayout.create().horizontal().justifyContent()
                .spaceAround().alignContent().end().nowrap().build();
        naviBar.addComponents(new Menu());
        naviBar.setHeight("40px");

        VerticalLayout content = new VerticalLayout(naviBar, container);
        content.setSizeFull();
        setContent(content);
        setSizeFull();
        container.setSizeFull();
        content.setExpandRatio(container, 1.0f);
        navigator.navigateTo("AboutView");
    }
}