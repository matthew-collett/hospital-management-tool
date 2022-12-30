package team6.project.view;

import team6.project.controller.*;
import team6.project.view.components.HeaderPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.Font.ITALIC;
import static java.awt.Font.SANS_SERIF;
import static java.lang.String.format;
import static javax.swing.Box.createVerticalStrut;

public class SearchView extends JPanel {

    public SearchView(final PatientApp app) {

        setLayout(new BorderLayout());

        add(new HeaderPanel("Search for a Patient"), NORTH);

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        final JLabel message = new JLabel(format("Hello, %s", app.getModel().getUserType()));
        message.setFont(new Font(SANS_SERIF, ITALIC, 14));
        mainPanel.add(message, NORTH);

        final JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));

        searchPanel.add(createVerticalStrut(32));

        final DefaultListModel<String> searchModel = new DefaultListModel<>();
        final List<String> patients = app.getModel().getPatientDisplay();
        searchModel.addAll(patients);

        final JButton viewPatientButton = new JButton("View Patient Information");
        viewPatientButton.setEnabled(false);
        app.getRootPane().setDefaultButton(viewPatientButton);

        final JList<String> searchList = new JList<>(searchModel);
        searchList.setVisibleRowCount(5);

        final JButton newPatientButton = new JButton("Add New Patient");
        newPatientButton.addActionListener(e -> app.setView(new NewPatientView(app)));

        searchList.addListSelectionListener(e -> viewPatientButton.setEnabled(true));
        viewPatientButton.addActionListener(new ViewPatientAction(app, searchList));

        final JTextField searchField = new JTextField();
        searchField.putClientProperty("JTextField.placeholderText", "Type to filter patients");
        searchField.getDocument().addDocumentListener(new SearchAction(searchField::getText, searchList.getModel(), patients));
        searchPanel.add(searchField);
        searchPanel.add(new JScrollPane(searchList));
        searchPanel.add(createVerticalStrut(32));

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 0 ,6));

        buttonPanel.add(viewPatientButton);
        buttonPanel.add(newPatientButton);

        final JButton logoutButton = new JButton("Log Out");
        logoutButton.addActionListener(new LogoutAction(app));
        buttonPanel.add(logoutButton);

        searchPanel.add(buttonPanel);
        mainPanel.add(searchPanel, CENTER);
        add(mainPanel, CENTER);
    }

    @Override
    public Insets getInsets() {
        return new Insets(16, 32, 32, 32);
    }


}
