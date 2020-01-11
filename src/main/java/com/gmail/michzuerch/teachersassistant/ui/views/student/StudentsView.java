package com.gmail.michzuerch.teachersassistant.ui.views.student;

import com.gmail.michzuerch.teachersassistant.app.security.CurrentUser;
import com.gmail.michzuerch.teachersassistant.backend.data.Role;
import com.gmail.michzuerch.teachersassistant.backend.data.entity.Student;
import com.gmail.michzuerch.teachersassistant.backend.service.StudentService;
import com.gmail.michzuerch.teachersassistant.ui.MainView;
import com.gmail.michzuerch.teachersassistant.ui.config.Pages;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import static com.gmail.michzuerch.teachersassistant.ui.config.Pages.PAGE_STUDENTS;

@Route(value = PAGE_STUDENTS, layout = MainView.class)
@PageTitle(Pages.TITLE_STUDENTS)
@Secured(Role.ADMIN)
public class StudentsView extends Div {
    private static final long serialVersionUID = 1L;

    @Autowired
    public StudentsView(StudentService service, CurrentUser currentUser) {
        Grid<Student> grid = new Grid<>(Student.class, false);
        grid.addColumns("id");
        grid.setItems(service.getRepository().findAll());
        add(grid);
    }


}