package com.gmail.michzuerch.LehrerVerwaltung.servlet;

import com.gmail.michzuerch.LehrerVerwaltung.presentation.ui.LehrerVerwaltungUI;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.cdi.server.VaadinCDIServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "LehrerVerwaltungServlet", asyncSupported = true, value = {"/ui/*", "/VAADIN/*"})
@VaadinServletConfiguration(ui = LehrerVerwaltungUI.class, productionMode = false)
public class LehrerVerwaltungServlet extends VaadinCDIServlet {
}
