package team6.project.controller;

import team6.project.model.AppModel;
import team6.project.view.InfoView;
import team6.project.view.PatientApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.util.Arrays.asList;

public class ViewPatientAction implements ActionListener {

    final PatientApp app;
    final JList<String> list;

    public ViewPatientAction(final PatientApp app, final JList<String> list) {
        this.app = app;
        this.list = list;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final AppModel model = app.getModel();
        model.setCurrentPatient(asList(list.getSelectedValue().split(" ")).get(0));
        app.setView(new InfoView(app));
    }
}
