package team6.project.controller.helper;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import team6.project.model.types.Doctor;
import team6.project.model.types.Patient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.stream.IntStream.range;
import static org.apache.commons.io.FileUtils.writeByteArrayToFile;
import static org.apache.commons.io.FilenameUtils.getExtension;

public class ExcelHelper {

    public static List<Patient> readPatients(final Path path) {

        final File file = path.toFile();

        if (!getExtension(file.getName()).equals("xlsx")) {
            throw new IllegalArgumentException(format("File at: %s is not a valid excel file", path));
        }
        if (!file.exists()) {
            throw new IllegalArgumentException(format("Excel file does not exist at: %s", path));
        }

        try (final XSSFWorkbook workbook = new XSSFWorkbook(file)) {
            final XSSFSheet sheet = workbook.getSheetAt(0);
            return iteratorToStream(sheet.rowIterator()).skip(1).map(ExcelHelper::readRow).toList();
        } catch (final IOException | InvalidFormatException e) {
            throw new IllegalStateException(format("Error reading excel from file at: %s", path));
        }
    }

    private static Patient readRow(final Row row) {
        final List<String> data = iteratorToStream(row.cellIterator()).map(Cell::toString).toList();
        return new Patient(data.get(0), data.get(1), data.get(2), LocalDate.parse(data.get(3)), data.get(4),
                parseInt(data.get(5)), Doctor.parse(data.get(6)), data.get(7), data.get(8), data.get(9));
    }

    public static void writePatient(final Path path, final Patient patient) {

        final XSSFWorkbook workbook;
        final XSSFSheet sheet;
        final File file = path.toFile();

        if (!file.exists()) {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Patient Data");
            writeRow(sheet, 0, "Patient ID", "Title", "Name", "Date of Birth",
                    "Phone", "Room", "Doctor", "Condition(s)", "Medications(s)", "Emergency Contact");
        } else {
            try {
                workbook = new XSSFWorkbook(file);
            } catch (final IOException | InvalidFormatException e) {
                throw new IllegalStateException(format("Error reading excel from file at: %s", path));
            }
            sheet = workbook.getSheetAt(0);
        }

        try (workbook) {
            writeRow(sheet, sheet.getLastRowNum() + 1, patient.getId(), patient.getTitle(), patient.getName(), patient.getDob(),
                    patient.getPhone(), patient.getRoom(), patient.getDoctor(), patient.getConditions(), patient.getMedications(), patient.getEPhone());

            range(0, getLastCellNum(sheet.getRow(0))).forEach(sheet::autoSizeColumn);

            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            writeByteArrayToFile(file, out.toByteArray());
        } catch (final IOException e) {
            throw new IllegalStateException(format("Error writing excel to file at: %s", path));
        }
    }

    public static void updatePatient(final Path path, final Patient patient) {

        final XSSFWorkbook workbook;
        final XSSFSheet sheet;
        final File file = path.toFile();

        if (!file.exists()) {
            throw new IllegalStateException(format("No excel file to update at: %s", path));
        } else {
            try {
                workbook = new XSSFWorkbook(file);
            } catch (final IOException | InvalidFormatException e) {
                throw new IllegalStateException(format("Error reading excel from file at: %s", path));
            }
            sheet = workbook.getSheetAt(0);
        }

        try (workbook) {
            final Row row = iteratorToStream(sheet.rowIterator()).filter(r -> r.getCell(0).getStringCellValue().equals(patient.getId())).findFirst().orElseThrow();

            final List<Object> data = asList(patient.getId(), patient.getTitle(), patient.getName(), patient.getDob(),
                    patient.getPhone(), patient.getRoom(), patient.getDoctor(), patient.getConditions(),
                    patient.getMedications(), patient.getEPhone());

            range(0, data.size()).forEach(i -> row.getCell(i).setCellValue(valueOf(data.get(i))));

            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            writeByteArrayToFile(file, out.toByteArray());
        } catch (final IOException e) {
            throw new IllegalStateException(format("Error writing excel to file at: %s", path));
        }
    }

    private static void writeRow(final XSSFSheet sheet, final int rowNum, final Object... data) {
        final Row row = sheet.createRow(rowNum);
        stream(data).forEach(o -> writeCell(row, o));
    }

    private static void writeCell(final Row row, final Object o) {
        final Cell cell = row.createCell(getLastCellNum(row));
        cell.setCellValue(valueOf(o));
    }

    private static int getLastCellNum(final Row row) {
        return row.getLastCellNum() == -1 ? 0 : row.getLastCellNum();
    }

    public static <T> Stream<T> iteratorToStream(final Iterator<T> iterator) {
        final Spliterator<T> spliterator = spliteratorUnknownSize(iterator, 0);
        return StreamSupport.stream(spliterator, false);
    }
}
