package com.gmail.michzuerch.TeachersAssistant.presentation.ui;


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

//        MenuBar.MenuItem stammdatenComponents = menuBar.addItem("Stammdaten", VaadinIcons.FACTORY, null);

        menuBar.addItem("School", VaadinIcons.PUZZLE_PIECE, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getNavigator().navigateTo("SchoolSubjectView");
            }
        });

        menuBar.addItem("Teacher", VaadinIcons.PUZZLE_PIECE, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getNavigator().navigateTo("TeacherView");
            }
        });

        menuBar.addItem("SchoolClass", VaadinIcons.PUZZLE_PIECE, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getNavigator().navigateTo("SchoolClassView");
            }
        });

        menuBar.addItem("Schüler", VaadinIcons.PUZZLE_PIECE, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getNavigator().navigateTo("StudentView");
            }
        });

        menuBar.addItem("Classroom", VaadinIcons.DIPLOMA, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getNavigator().navigateTo("ClassroomView");
            }
        });

        menuBar.addItem("SchoolSubject", VaadinIcons.TRAIN, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getNavigator().navigateTo("SchoolSubjectView");
            }
        });

        menuBar.addItem("SchoolGrade", VaadinIcons.DIPLOMA, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getNavigator().navigateTo("SchoolGradeView");
            }
        });

        menuBar.addItem("Stundenplan", VaadinIcons.ENTER, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getNavigator().navigateTo("StundenplanView");
            }
        });

        MenuBar.MenuItem reports = menuBar.addItem("Reports", VaadinIcons.OFFICE, null);

        reports.addItem("Report Jasper", VaadinIcons.ACCORDION_MENU, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getNavigator().navigateTo("ReportJasperView");
            }
        });
        reports.addItem("Report Jasper Image", VaadinIcons.ACCORDION_MENU, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getNavigator().navigateTo("ReportJasperImageView");
            }
        });
        reports.addItem("Report FOP", VaadinIcons.ACCORDION_MENU, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getNavigator().navigateTo("ReportFOPView");
            }
        });
        reports.addItem("Report FOP Image", VaadinIcons.ACCORDION_MENU, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getNavigator().navigateTo("ReportFOPImageView");
            }
        });


        reports.addItem("Report CSS", VaadinIcons.ACCORDION_MENU, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getNavigator().navigateTo("ReportCSSView");
            }
        });
        reports.addItem("Report CSS Image", VaadinIcons.ACCORDION_MENU, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getNavigator().navigateTo("ReportCSSImageView");
            }
        });

        MenuBar.MenuItem systemwartung = menuBar.addItem("Systemwartung", VaadinIcons.UMBRELLA, null);

        systemwartung.addItem("Backup", VaadinIcons.WORKPLACE, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getNavigator().navigateTo("BackupView");
            }
        });

        MenuBar.MenuItem tests = menuBar.addItem("Tests", VaadinIcons.DOCTOR, null);
        tests.addItem("Testdaten School, Teacher, SchoolClass, Schüler", VaadinIcons.AIRPLANE, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                getUI().getNavigator().navigateTo("TestdatenView");
            }
        });

        layout.addComponent(menuBar);
        setCompositionRoot(layout);
    }
}