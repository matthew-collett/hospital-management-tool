package team6.project.helper;

import static java.nio.file.Files.isDirectory;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

public class PathHelper {

    public static Path getDirectoryPath(final Class<?> source) {
        final URL location = source.getProtectionDomain().getCodeSource().getLocation();
        final Path path = Path.of(toURI(location));
        return isDirectory(path) ? path : path.getParent();
    }

    private static URI toURI(final URL location) {
        try {
            return location.toURI();
        } catch (final URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }
}

