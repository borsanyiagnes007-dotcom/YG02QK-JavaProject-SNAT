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
            //e.printStackTrace();
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
        //System.out.println("main.java.File location: " + path.toAbsolutePath());
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

            //System.out.println("Save deleted!");

        } catch (IOException e) {
            System.out.println("Error deleting save: " + e.getMessage());
        }
    }

     /*
    public static void deleteLine(String fileName, int index) {
        Path path = Path.of("src/" + fileName);

        try {
            System.out.println("main.java.File location: " + path.toAbsolutePath());

            List<String> lines = Files.readAllLines(path);

            System.out.println("Lines before deletion: " + lines.size());

            if (index < 0 || index >= lines.size()) {
                System.out.println("Invalid save index!");
                return;
            }

            System.out.println("Deleting: " + lines.get(index));

            lines.remove(index);

            Files.write(
                    path,
                    lines,
                    StandardCharsets.UTF_8
            );

            System.out.println("Lines after deletion: " +
                    Files.readAllLines(path).size());

            System.out.println("Save deleted!");

        } catch (IOException e) {
            System.out.println("Error deleting save: " + e.getMessage());
            e.printStackTrace();
        }
    }
    */

}
