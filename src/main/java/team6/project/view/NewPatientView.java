package team6.project.view;

import team6.project.controller.AddPatientAction;
import team6.project.view.components.HeaderPanel;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import static java.lang.String.format;
import static java.util.Arrays.stream;

public class NewPatientView extends InfoView {

    public NewPatientView(final PatientApp app) {
        super(app);

        remove(getComponent(2));

        if (this instanceof UpdatePatientView) {
            add(new HeaderPanel(format("Update Patient %s", app.getModel().getCurrentPatient().getId())), NORTH);
        } else {
            add(new HeaderPanel("Add a New Patient"), NORTH);
        }

        final JScrollPane scrollPane = (JScrollPane) stream(getComponents()).filter(c -> c instanceof JScrollPane).findFirst().orElseThrow();
        final JTable infoTable = (JTable) scrollPane.getViewport().getView();
        infoTable.setEnabled(true);

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 8, 0));

        final JButton discardButton = new JButton("Discard");
        discardButton.addActionListener(e -> app.setView(new SearchView(app)));
        buttonPanel.add(discardButton);

        final JButton changeButton = new JButton();
        changeButton.addActionListener(new AddPatientAction(app, infoTable));

        if (this instanceof UpdatePatientView) {
            changeButton.setText("Update");
        } else {
            changeButton.setText("Add");
        }

        app.getRootPane().setDefaultButton(changeButton);
        buttonPanel.add(changeButton);

        add(buttonPanel, SOUTH);
    }


    @Override
    public Insets getInsets() {
        return new Insets(16, 32, 32, 32);
    }

}
