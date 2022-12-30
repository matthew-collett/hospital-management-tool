package team6.tests;

import org.junit.jupiter.api.Test;
import team6.project.controller.LoginAction;
import team6.project.model.AppModel;
import team6.project.view.LoginView;
import team6.project.view.PatientApp;
import team6.project.view.PatientAppImpl;
import team6.project.view.SearchView;

import javax.swing.*;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static team6.project.model.types.UserType.*;

public class TestLoginAction {

    private final PatientApp testApp = new PatientAppImpl();
    private final AppModel testModel = testApp.getModel();
    private final JButton testLoginButton = new JButton();
    Supplier<String> testUsernameSupplier;
    Supplier<char[]> testPasswordSupplier;

    @Test
    void testDoctorLoginAction() {

        testUsernameSupplier = () -> "doctor";
        testPasswordSupplier = () -> new char[]{'1', '6'};

        testLoginButton.addActionListener(new LoginAction(testApp, testUsernameSupplier, testPasswordSupplier));
        testLoginButton.doClick();

        assertEquals(Doctor, testModel.getUserType());
    }

    @Test
    void testReceptionLoginAction() {

        testUsernameSupplier = () -> "reception";
        testPasswordSupplier = () -> new char[]{'3', '9'};

        testLoginButton.addActionListener(new LoginAction(testApp, testUsernameSupplier, testPasswordSupplier));
        testLoginButton.doClick();

        assertEquals(Reception, testModel.getUserType());
    }

    @Test
    void testInvalidUsernameLoginAction() {

        testUsernameSupplier = () -> "test";
        testPasswordSupplier = () -> new char[]{'1', '6'};

        testLoginButton.addActionListener(new LoginAction(testApp, testUsernameSupplier, testPasswordSupplier));
        testLoginButton.doClick();

        assertEquals(INVALID, testModel.getUserType());
    }

    @Test
    void testInvalidPasswordLoginAction() {

        testUsernameSupplier = () -> "reception";
        testPasswordSupplier = () -> new char[]{'0', '0'};

        testLoginButton.addActionListener(new LoginAction(testApp, testUsernameSupplier, testPasswordSupplier));
        testLoginButton.doClick();

        assertEquals(INVALID, testModel.getUserType());
    }

    @Test
    void testValidViewChangeLoginAction() {

        testUsernameSupplier = () -> "reception";
        testPasswordSupplier = () -> new char[]{'3', '9'};

        testLoginButton.addActionListener(new LoginAction(testApp, testUsernameSupplier, testPasswordSupplier));
        testLoginButton.doClick();

        assertEquals(new SearchView(testApp), testApp.getContentPane());
    }

    @Test
    void testInvalidViewChangeLoginAction() {

        testLoginButton.addActionListener(new LoginAction(testApp, null, null));
        testLoginButton.doClick();

        assertEquals(new LoginView(testApp), testApp.getContentPane());
    }
}
