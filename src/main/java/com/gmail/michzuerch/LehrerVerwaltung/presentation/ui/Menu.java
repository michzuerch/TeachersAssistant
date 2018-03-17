package com.gmail.michzuerch.LehrerVerwaltung.presentation.ui;


import com.vaadin.cdi.ViewScoped;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.MenuBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by michzuerch on 26.10.15.
 */

@ViewScoped
public class Menu extends CustomComponent {
    private static Logger logger = LoggerFactory.getLogger(Menu.class.getName());
    CssLayout layout = new CssLayout();

    public Menu() {
        ThemeResource resourceLogo = new ThemeResource("img/pc-small.png");
        MenuBar menuBar = new MenuBar();
        MenuBar.MenuItem aboutItem = menuBar.addItem("About", new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem menuItem) {
                getUI().getNavigator().navigateTo("AboutView");
            }
        });
        aboutItem.setIcon(resourceLogo);

        MenuBar.MenuItem stammdatenComponents = menuBar.addItem("Stammdaten", VaadinIcons.FACTORY, null);

        stammdatenComponents.addItem("Schule", VaadinIcons.PUZZLE_PIECE, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getNavigator().navigateTo("SchuleView");
            }
        });

        stammdatenComponents.addItem("Lehrer", VaadinIcons.PUZZLE_PIECE, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getNavigator().navigateTo("LehrerView");
            }
        });

        stammdatenComponents.addItem("Klasse", VaadinIcons.PUZZLE_PIECE, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getNavigator().navigateTo("KlasseView");
            }
        });

        stammdatenComponents.addItem("Schüler", VaadinIcons.PUZZLE_PIECE, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getNavigator().navigateTo("SchuelerView");
            }
        });


        layout.addComponent(menuBar);
        setCompositionRoot(layout);
    }
}
