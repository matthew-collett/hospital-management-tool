package team6.project.model;

import team6.project.model.types.Patient;
import team6.project.model.types.UserType;

import java.util.List;

public interface AppModel {

    UserType getUserType();

    void setUserType(UserType user);

    List<String> getPatientDisplay();

    void setPatients(List<Patient> patients);

    Patient getCurrentPatient();

    void setCurrentPatient(String patientId);
}
