package com.gmail.michzuerch.TeachersAssistant.servlet;

import com.gmail.michzuerch.TeachersAssistant.presentation.ui.TeachersAssistantUI;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.cdi.server.VaadinCDIServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "TeachersAssistantServlet", asyncSupported = true, value = {"/ui/*", "/VAADIN/*"})
@VaadinServletConfiguration(ui = TeachersAssistantUI.class, productionMode = false)
public class TeachersAssistantServlet extends VaadinCDIServlet {
}
