//ezek a file műveletek, mint az írás és olvasás

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class File {

    public static String load(String path) {
        try {
            String content = Files.readString(Path.of(path));
            return content;
        } catch (IOException e) {
            return "Couldn't load " + path;
        }
    }

    public static List<String> readLines(String path) {
        try {
            return Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void addLine(String path, String line) {
        try {
            Files.writeString(
                    Path.of(path),
                    line + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }


    public static void deleteLine(String fileName, int index) {
        Path path = Path.of(fileName);
        try {
            List<String> lines = Files.readAllLines(path);

            if (index < 0 || index >= lines.size()) {
                System.out.println("Invalid save index!");
                return;
            }

            lines.remove(index);

            Files.write(
                    path,
                    lines,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );

        } catch (IOException e) {
            System.out.println("Error deleting save: " + e.getMessage());
        }
    }
}
