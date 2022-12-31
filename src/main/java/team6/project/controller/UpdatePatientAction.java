package team6.project.controller;

import team6.project.controller.helper.ExcelHelper;
import team6.project.view.InfoView;
import team6.project.view.PatientApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static team6.project.helper.PathHelper.getDirectoryPath;

public class UpdatePatientAction implements ActionListener {

    private final PatientApp app;
    private final JTable infoTable;

    public UpdatePatientAction(final PatientApp app, final JTable infoTable) {
        this.app = app;
        this.infoTable = infoTable;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        infoTable.getCellEditor().stopCellEditing();
        app.getModel().getCurrentPatient().setTitle(valueOf(infoTable.getValueAt(1, 1)));
        app.getModel().getCurrentPatient().setName(valueOf(infoTable.getValueAt(2, 1)));
        app.getModel().getCurrentPatient().setPhone(valueOf(infoTable.getValueAt(4, 1)));
        app.getModel().getCurrentPatient().setRoom(parseInt(valueOf(infoTable.getValueAt(0, 3))));
        app.getModel().getCurrentPatient().setConditions(valueOf(infoTable.getValueAt(2, 3)));
        app.getModel().getCurrentPatient().setMedications(valueOf(infoTable.getValueAt(3, 3)));
        app.getModel().getCurrentPatient().setEPhone(valueOf(infoTable.getValueAt(4, 3)));
        ExcelHelper.updatePatient(getDirectoryPath(getClass()).resolve("PatientData.xlsx"), app.getModel().getCurrentPatient());
        app.setView(new InfoView(app));
    }

}
