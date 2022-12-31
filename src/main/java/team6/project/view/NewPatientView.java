package team6.project.view;

import team6.project.controller.AddPatientAction;
import team6.project.controller.ExitInfoAction;
import team6.project.controller.UpdatePatientAction;
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


        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 8, 0));

        final JButton discardButton = new JButton("Discard");
        discardButton.addActionListener(new ExitInfoAction(app));
        buttonPanel.add(discardButton);

        final JButton changeButton = new JButton();

        if (this instanceof UpdatePatientView) {
            changeButton.addActionListener(new UpdatePatientAction(app, infoTable));
            changeButton.setText("Update");

        } else {
            changeButton.addActionListener(new AddPatientAction(app, infoTable));
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
