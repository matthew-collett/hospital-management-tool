package team6.tests;

import org.junit.jupiter.api.Test;
import team6.project.model.types.Doctor;
import team6.project.model.types.Patient;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static java.util.Calendar.FEBRUARY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static team6.project.controller.helper.ExcelHelper.*;
import static team6.project.controller.helper.ExcelHelper.writePatient;
import static team6.project.helper.PathHelper.getDirectoryPath;

public class TestExcelHelper {

    @Test
    void testReadWritePatient() {

        final Patient testPatient = new Patient("344523", "Mr.", "John Smith", LocalDate.of(2000, FEBRUARY, 1),
                "506-849-2342", 202, new Doctor("234234", "Dorthy Maclean", "Neurology"),
                "Headache", "Tylenol 500mg", "506-434-6753");

        final Path testFilePath = getDirectoryPath(getClass()).resolve("TestExcelHelper.xlsx");
        writePatient(testFilePath, testPatient);

        final List<Patient> patients = readPatients(testFilePath);
        final Patient patient = patients.get(0);

        assertEquals(patient.getId(), testPatient.getId());
        assertEquals(patient.getTitle(), testPatient.getTitle());
        assertEquals(patient.getName(), testPatient.getName());
        assertEquals(patient.getDob(), testPatient.getDob());
        assertEquals(patient.getPhone(), testPatient.getPhone());
        assertEquals(patient.getRoom(), testPatient.getRoom());
        assertEquals(patient.getDoctor(), testPatient.getDoctor());
        assertEquals(patient.getConditions(), testPatient.getConditions());
        assertEquals(patient.getMedications(), testPatient.getMedications());
        assertEquals(patient.getEPhone(), testPatient.getEPhone());
    }
}
