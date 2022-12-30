package team6.project.view;

import team6.project.controller.ExitInfoAction;
import team6.project.controller.LogoutAction;
import team6.project.controller.UpdatePatientAction;
import team6.project.view.components.HeaderPanel;
import team6.project.view.components.InfoTable;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import static java.util.stream.IntStream.range;
import static javax.swing.SwingConstants.CENTER;

public class InfoView extends JPanel {

    public InfoView(final PatientApp app) {

        setLayout(new BorderLayout(0, 16));

        add(new HeaderPanel("Patient Information"), NORTH);

        final JTable infoTable = new InfoTable(5, 4);
        infoTable.setEnabled(false);
        infoTable.setTableHeader(null);
        infoTable.setBackground(new Color(0xfaf9f6));
        infoTable.setRowHeight(32);
        range(0, infoTable.getColumnModel().getColumnCount()).forEach(i -> infoTable.getColumnModel().getColumn(i).setPreferredWidth(64));

        infoTable.setValueAt("<html><b>Patient ID:</b><html>", 0, 0);
        infoTable.setValueAt("<html><b>Title:</b><html>", 1, 0);
        infoTable.setValueAt("<html><b>First, Last Name:</b><html>", 2, 0);
        infoTable.setValueAt("<html><b>Date of Birth:</b><html>", 3, 0);
        infoTable.setValueAt("<html><b>Phone Number:</b><html>", 4, 0);

        infoTable.setValueAt("<html><b>Room Number:</b><html>", 0, 2);
        infoTable.setValueAt("<html><b>Doctor:</b><html>", 1, 2);
        infoTable.setValueAt("<html><b>Condition(s):</b><html>", 2, 2);
        infoTable.setValueAt("<html><b>Medication(s):</b><html>", 3, 2);
        infoTable.setValueAt("<html><b>Emergency Contact:</b><html>", 4, 2);

        if (app.getModel().getCurrentPatient() != null) {

            infoTable.setValueAt(app.getModel().getCurrentPatient().getId(), 0, 1);
            infoTable.setValueAt(app.getModel().getCurrentPatient().getTitle(), 1, 1);
            infoTable.setValueAt(app.getModel().getCurrentPatient().getName(), 2, 1);
            infoTable.setValueAt(app.getModel().getCurrentPatient().getDob(), 3, 1);
            infoTable.setValueAt(app.getModel().getCurrentPatient().getPhone(), 4, 1);

            infoTable.setValueAt(app.getModel().getCurrentPatient().getRoom(), 0, 3);
            infoTable.setValueAt(app.getModel().getCurrentPatient().getDoctor().display(), 1, 3);
            infoTable.setValueAt(app.getModel().getCurrentPatient().getConditions(), 2, 3);
            infoTable.setValueAt(app.getModel().getCurrentPatient().getMedications(), 3, 3);
            infoTable.setValueAt(app.getModel().getCurrentPatient().getEPhone(), 4, 3);
        }

        final JScrollPane scrollPane = new JScrollPane(infoTable);
        scrollPane.setPreferredSize(new Dimension(256, 165));
        scrollPane.setBorder(new LineBorder(new Color(0xd3d3d3), 1));
        add(scrollPane, CENTER);

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 0, 8));

        final JButton updateButton = new JButton("Update Patient");
        app.getRootPane().setDefaultButton(updateButton);
        updateButton.addActionListener(new UpdatePatientAction(app, infoTable));
        buttonPanel.add(updateButton);

        final JButton exitButton = new JButton("Exit Patient Information");
        exitButton.addActionListener(new ExitInfoAction(app));
        buttonPanel.add(exitButton);

        final JButton logoutButton = new JButton("Log Out");
        logoutButton.addActionListener(new LogoutAction(app));
        buttonPanel.add(logoutButton);

        add(buttonPanel, SOUTH);
    }

    @Override
    public Insets getInsets() {
        return new Insets(16, 32, 32, 32);
    }

}
