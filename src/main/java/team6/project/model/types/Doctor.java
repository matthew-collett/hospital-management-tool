package team6.project.model.types;

import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;

public record Doctor(String id, String name, String specialty) {

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
        return doctor.id().equals(this.id());
    }
}