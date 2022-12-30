package team6.project.model.types;

import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;

public class Doctor {
    private final String id;
    private final String name;
    private final String specialty;

    public Doctor(final String id, final String name, final String specialty) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String display() {
        return format("Dr. %s", name);
    }
    @Override
    public String toString() {
        return format("%s, %s, %s", id, name, specialty);
    }

    public static Doctor parse(final String doctor) {
        final List<String> data = asList(doctor.split(", "));
        return new Doctor(data.get(0), data.get(1), data.get(2));
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof final Doctor doctor)) {
            return false;
        }
        return doctor.getId().equals(this.getId());
    }
}