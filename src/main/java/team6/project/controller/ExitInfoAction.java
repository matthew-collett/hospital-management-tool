package team6.project.controller;

import team6.project.model.AppModel;
import team6.project.view.PatientApp;
import team6.project.view.SearchView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitInfoAction implements ActionListener {

    private final PatientApp app;

    public ExitInfoAction(final PatientApp app) {
        this.app = app;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final AppModel model = app.getModel();
        model.setCurrentPatient(null);
        app.setView(new SearchView(app));
    }
}
