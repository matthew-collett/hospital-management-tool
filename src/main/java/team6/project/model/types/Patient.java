package team6.project.model.types;

import java.time.LocalDate;

import static java.lang.String.format;

public class Patient {
    private final String id;
    private final String title;
    private final String name;
    private final LocalDate dob;
    private String phone;
    private int room;
    private final Doctor doctor;
    private String conditions;

    private String medications;
    private String ePhone;

    public Patient(final String id, final String title, final String name, final LocalDate dob, final String phone, final int room,
                   final Doctor doctor, final String conditions, final String medications, final String ePhone) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.dob = dob;
        this.phone = phone;
        this.room = room;
        this.doctor = doctor;
        this.conditions = conditions;
        this.medications = medications;
        this.ePhone = ePhone;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(final int room) {
        this.room = room;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(final String conditions) {
        this.conditions = conditions;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(final String medications) {
        this.medications = medications;
    }

    public String getEPhone() {
        return ePhone;
    }

    public void setEPhone(final String ePhone) {
        this.ePhone = ePhone;
    }

    public String getDisplay() {
        return format("%s - %s", id, name);
    }

}
