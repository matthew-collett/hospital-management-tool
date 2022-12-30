package team6.project.controller;

import team6.project.view.PatientApp;
import team6.project.view.UpdatePatientView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePatientAction implements ActionListener {

    private final PatientApp app;
    private final JTable infoTable;

    public UpdatePatientAction(final PatientApp app, final JTable infoTable) {
        this.app = app;
        this.infoTable = infoTable;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        infoTable.setEnabled(true);
        app.setView(new UpdatePatientView(app));
    }
}
