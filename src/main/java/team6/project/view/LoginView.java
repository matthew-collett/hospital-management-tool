package team6.project.view;

import team6.project.controller.LoginAction;
import team6.project.view.components.HeaderPanel;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import static javax.swing.SwingConstants.CENTER;
import static team6.project.model.types.UserType.INVALID;

public class LoginView extends JPanel {

    public LoginView(final PatientApp app) {

        setLayout(new BorderLayout());

        add(new HeaderPanel("Log In"), NORTH);

        final JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(5, 1));

        final JLabel usernameLabel = new JLabel("Username");
        fieldPanel.add(usernameLabel);

        final JTextField usernameField = new JTextField();
        fieldPanel.add(usernameField);

        final JLabel passwordLabel = new JLabel("Password");
        fieldPanel.add(passwordLabel);

        final JPasswordField passwordField = new JPasswordField();
        fieldPanel.add(passwordField);

        final JLabel errorLabel = new JLabel();
        fieldPanel.add(errorLabel);
        errorLabel.setHorizontalAlignment(CENTER);

        add(fieldPanel, BorderLayout.CENTER);

        final JButton loginButton = new JButton("Log In");
        app.getRootPane().setDefaultButton(loginButton);
        loginButton.addActionListener(new LoginAction(app, usernameField::getText, passwordField::getPassword));

        add(loginButton, SOUTH);

        if (app.getModel().getUserType() == INVALID) {
            errorLabel.setText("Invalid Username or Password");
            errorLabel.setForeground(new Color(0xff4444));
            usernameField.putClientProperty("JComponent.outline", "error");
            passwordField.putClientProperty("JComponent.outline", "error");
        }
    }

    @Override
    public Insets getInsets() {
        return new Insets(16, 32, 32, 32);
    }
}
