package org.umg;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
    private Profesor p =new Profesor();
    private Estudiante e1;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        final HorizontalLayout hLayout = new HorizontalLayout();
        final VerticalLayout layout1 = new VerticalLayout();

        final TextField name = new TextField();
        name.setCaption("Nombre:");

        final TextField age = new TextField();
        age.setCaption("Edad:");

        final TextField materia = new TextField();
        materia.setCaption("Materia");

        final TextField nota = new TextField();
        nota.setCaption("Nota");

        final TextField evaluacion = new TextField();
        evaluacion.setCaption("Evaluacion");


        List<Estudiante> estudiantes= new ArrayList<>();
        //estudiantes.add(new Estudiante("jose", 25));

        Grid<Estudiante> grid = new Grid<>();
        grid.setWidth("100%");
        grid.setItems(estudiantes);
        grid.addColumn(Estudiante::getNombre).setCaption("nombre");
        grid.addColumn(Estudiante::getEdad).setCaption("edad");
        grid.addColumn(Estudiante::getProm).setCaption("promedio");



        Button bmateria = new Button ("Ingresar Materia");
        Button bprodest = new Button("Obtener Promedio");
        Button bmpromeido = new Button("Mejor Promedio");
        Button button = new Button("Añadir");

        button.addClickListener( e -> {
            //layout.addComponent(new Label("Thanks " + name.getValue()
              //      + ", it works!"));
            // agregar a la lista

            //estudiantes.add(new Estudiante(name.getValue(), Integer.parseInt(age.getValue()) ));
            estudiantes.add(new Estudiante(name.getValue(), Integer.parseInt(age.getValue()), Integer.parseInt(.getValue()) ));
            grid.setItems(estudiantes);

            e1 =new Estudiante("", 0);
            e1.setNombre(name.getValue());
            e1.setEdad(Integer.parseInt(age.getValue()));

            Notification.show("Estudiantes Ingresado");

        });

        bmateria.addClickListener( e -> {
            System.out.println("HOLA MUNDO");
            e1.addAsignatura(new Asignatura(materia.getValue(),Integer.parseInt(nota.getValue()),Integer.parseInt(evaluacion.getValue())));
            materia.clear();
            nota.clear();
            evaluacion.clear();
            Notification.show("Materia Ingresada");
        });

        bprodest.addClickListener(e -> {
            p.addEstudiante(e1);
            Notification.show("Promedio Estudiante");
            name.clear();
            age.clear();
        });

        bmpromeido.addClickListener(e -> {
            Notification.show(p.estudianteMasDestacado().getNombre());
            name.setCaption(p.estudianteMasDestacado().getNombre());
        });

        hLayout.addComponents(name, age, button);
        hLayout.setComponentAlignment(button, Alignment.BOTTOM_RIGHT);
        layout.addComponents(hLayout, materia, nota, evaluacion, bmateria,bprodest, bmpromeido, grid);


        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
