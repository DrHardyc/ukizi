package ru.hardy.ukizi.view;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;

@Route
public class PlanVacation extends VerticalLayout {

    private final DatePicker labelDatePicker = new DatePicker();
    private final DatePicker placeholderDatePicker = new DatePicker();
    private final DatePicker valueDatePicker = new DatePicker();
    private final LocalDate now = LocalDate.now();



    public PlanVacation (){
        labelDatePicker.setLabel("Label");
        placeholderDatePicker.setPlaceholder("Placeholder");
        valueDatePicker.setValue(now);
        add(labelDatePicker, placeholderDatePicker, valueDatePicker);
    }
}
