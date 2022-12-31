package team6.project.controller;

import team6.project.controller.helper.ExcelHelper;
import team6.project.model.types.Doctor;
import team6.project.model.types.Patient;
import team6.project.view.PatientApp;
import team6.project.view.SearchView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static team6.project.helper.PathHelper.getDirectoryPath;
import static team6.project.model.types.Doctor.*;

public class AddPatientAction implements ActionListener {
    private final PatientApp app;
    private final JTable infoTable;

    public AddPatientAction(final PatientApp app, final JTable infoTable) {
        this.app = app;
        this.infoTable = infoTable;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {

        final Patient newPatient = new Patient(valueOf(infoTable.getValueAt(0, 1)), valueOf(infoTable.getValueAt(1, 1)),
                valueOf(infoTable.getValueAt(2, 1)), LocalDate.parse((CharSequence) infoTable.getValueAt(3, 1)),
                valueOf(infoTable.getValueAt(4, 1)), parseInt(valueOf(infoTable.getValueAt(0, 3))),
                Doctor.parse(valueOf(infoTable.getValueAt(1, 3))), valueOf(infoTable.getValueAt(2, 3)),
                valueOf(infoTable.getValueAt(3, 3)), valueOf(infoTable.getValueAt(4, 3)));

        ExcelHelper.writePatient(getDirectoryPath(getClass()).resolve("PatientData.xlsx"), newPatient);
        app.getModel().setPatients(ExcelHelper.readPatients(getDirectoryPath(getClass()).resolve("PatientData.xlsx")));
        app.getModel().setCurrentPatient(null);
        app.setView(new SearchView(app));
    }
}
