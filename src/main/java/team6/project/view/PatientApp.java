package team6.project.view;

import team6.project.model.AppModel;

import javax.swing.*;

public abstract class PatientApp extends JFrame {

    public PatientApp(final String title) {
        super(title);
    }

    public abstract void setView(final JPanel view);

    public abstract AppModel getModel();
}
