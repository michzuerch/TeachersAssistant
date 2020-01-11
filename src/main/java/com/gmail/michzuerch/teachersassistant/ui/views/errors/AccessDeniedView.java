package com.gmail.michzuerch.teachersassistant.ui.views.errors;

import com.gmail.michzuerch.teachersassistant.ui.MainView;
import com.gmail.michzuerch.teachersassistant.ui.config.Pages;
import com.gmail.michzuerch.teachersassistant.ui.exceptions.AccessDeniedException;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.*;
import com.vaadin.flow.templatemodel.TemplateModel;

import javax.servlet.http.HttpServletResponse;

@Tag("access-denied-view")
@JsModule("./src/views/errors/access-denied-view.js")
@ParentLayout(MainView.class)
@PageTitle(Pages.TITLE_ACCESS_DENIED)
@Route
public class AccessDeniedView extends PolymerTemplate<TemplateModel> implements HasErrorParameter<AccessDeniedException> {

    private static final long serialVersionUID = 1L;

    @Override
    public int setErrorParameter(BeforeEnterEvent beforeEnterEvent, ErrorParameter<AccessDeniedException> errorParameter) {
        return HttpServletResponse.SC_FORBIDDEN;
    }
}
