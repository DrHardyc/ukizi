package ru.hardy.ukizi.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hardy.ukizi.component.EmployeeEdit;
import ru.hardy.ukizi.domain.Employee;
import ru.hardy.ukizi.repo.EmployeeRepo;

@Route
public class MainView extends VerticalLayout {
    private final EmployeeRepo employeeRepo;

    private Grid<Employee> grid = new Grid<>(Employee.class);

    private final TextField filter = new TextField("", "Введите слово для поиска");
    private final Button addNewBtn = new Button("Добавить нового");
    private final HorizontalLayout toolbar = new HorizontalLayout(filter, addNewBtn);

    private final EmployeeEdit editor;

    @Autowired
    public MainView(EmployeeRepo employeeRepo, EmployeeEdit editor){
        this.employeeRepo = employeeRepo;
        this.editor = editor;

        add(toolbar, grid, editor);

        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> ShowEmployee(e.getValue()));

        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editEmployee(e.getValue());
        });

        addNewBtn.addClickListener(e -> editor.editEmployee(new Employee()));

        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            ShowEmployee(filter.getValue());
        });

        ShowEmployee("");
    }

    private void ShowEmployee(String name) {
        if (name.isEmpty()){
            grid.setItems(employeeRepo.findAll());
        } else{
            grid.setItems(employeeRepo.findByName(name));
        }

    }

}
