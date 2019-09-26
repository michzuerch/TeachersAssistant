package com.gmail.michzuerch.TeachersAssistant.frontend.page.school;

import com.gmail.michzuerch.TeachersAssistant.frontend.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Route(value = "School", layout = MainLayout.class)
public class SchoolPage extends VerticalLayout {
    /**
	 *
	 */
	private static final long serialVersionUID = -2256523663467795037L;

    @Autowired
    SchoolView view;

    @PostConstruct
    private void init() {
        add(view);
    }
}
