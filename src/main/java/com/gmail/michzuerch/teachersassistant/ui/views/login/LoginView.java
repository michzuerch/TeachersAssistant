package com.gmail.michzuerch.teachersassistant.ui.views.login;

import com.gmail.michzuerch.teachersassistant.app.security.SecurityUtils;
import com.gmail.michzuerch.teachersassistant.ui.config.Pages;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.*;

@Route
@PageTitle("TeachersAssistant")
@JsModule("./styles/shared-styles.js")
@Viewport(Pages.VIEWPORT)
public class LoginView extends LoginOverlay
        implements AfterNavigationObserver, BeforeEnterObserver {

    private static final long serialVersionUID = 1L;

    public LoginView() {
        LoginI18n i18n = LoginI18n.createDefault();
        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle("TeachersAssistant");
        i18n.getHeader().setDescription(
                "admin@michzuerch.gmail.com + admin\n");
        i18n.setAdditionalInformation(null);
        i18n.setForm(new LoginI18n.Form());
        i18n.getForm().setSubmit("Sign in");
        i18n.getForm().setTitle("Sign in");
        i18n.getForm().setUsername("Email");
        i18n.getForm().setPassword("Password");
        setI18n(i18n);
        setForgotPasswordButtonVisible(false);
        setAction("login");
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (SecurityUtils.isUserLoggedIn()) {
            UI.getCurrent().navigate(Pages.PAGE_STUDENTS);
            //event.forwardTo(StudentsView.class);
        } else {
            setOpened(true);
        }
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        setError(
                event.getLocation().getQueryParameters().getParameters().containsKey(
                        "error"));
    }

}
