package team6.project.controller;

import team6.project.controller.helper.ExcelHelper;
import team6.project.model.AppModel;
import team6.project.view.LoginView;
import team6.project.view.PatientApp;
import team6.project.view.SearchView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Supplier;

import static team6.project.helper.PathHelper.getDirectoryPath;
import static team6.project.model.types.UserType.*;

public class LoginAction implements ActionListener {

    private final PatientApp app;
    private final Supplier<String> usernameSupplier;
    private final Supplier<char[]> passwordSupplier;

    public LoginAction(final PatientApp app, final Supplier<String> usernameSupplier, final Supplier<char[]> passwordSupplier) {
        this.app = app;
        this.usernameSupplier = usernameSupplier;
        this.passwordSupplier = passwordSupplier;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {

        final AppModel model = app.getModel();
        model.setPatients(ExcelHelper.readPatients(getDirectoryPath(getClass()).resolve("PatientData.xlsx")));

        final String username = usernameSupplier.get();
        final String password = new String(passwordSupplier.get());

        switch (username.toLowerCase()) {
            case "doctor" -> model.setUserType(Doctor);
            case "reception" -> model.setUserType(Reception);
            default -> model.setUserType(INVALID);
        }

        if (model.getUserType() == Doctor && !(password.contains("1") && password.contains("6"))) {
            model.setUserType(INVALID);
        }

        if (model.getUserType() == Reception && !(password.contains("3") && password.contains("9"))) {
            model.setUserType(INVALID);
        }

        if (model.getUserType() == INVALID) {
            app.setView(new LoginView(app));
        } else {
            app.setView(new SearchView(app));
        }
    }
}
