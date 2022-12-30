package team6.project.model;

import team6.project.model.types.Patient;
import team6.project.model.types.UserType;

import java.util.List;

public class AppModelImpl implements AppModel {

    private UserType user;
    private Patient currentPatient;

    private List<Patient> patients;

    @Override
    public UserType getUserType() {
        return user;
    }

    @Override
    public void setUserType(final UserType user) {
        this.user = user;
    }

    @Override
    public List<String> getPatientDisplay() {
        return patients.stream().map(Patient::getDisplay).toList();
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public Patient getCurrentPatient() {
        return currentPatient;
    }

    public void setCurrentPatient(String patientId) {
        this.currentPatient = patients.stream().filter(p -> p.getId().equals(patientId)).findFirst().orElse(null);
    }
}

