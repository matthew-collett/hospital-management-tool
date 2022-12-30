package team6.project.controller;

import team6.project.model.AppModel;
import team6.project.view.LoginView;
import team6.project.view.PatientApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutAction implements ActionListener {

    private final PatientApp app;

    public LogoutAction(final PatientApp app) {
        this.app = app;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final AppModel model = app.getModel();
        model.setUserType(null);
        app.setView(new LoginView(app));
    }
}
