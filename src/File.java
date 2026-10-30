//ezek a file műveletek mint az írás és olvasás
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class File {

    public static String load(String path) {
        try {
            String content = Files.readString(Path.of("src", path));
            return content;
        } catch (IOException e) {
            return "Couldn't load " + path;
            //e.printStackTrace();
        }
    }

    public static List<String> readLines(String path) {
        try {
            return Files.readAllLines(Path.of("src", path));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static void addLine(String path, String line) {
        try {
            Files.writeString(
                    Path.of("src", path),
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
                return;
            }
            lines.remove(index);
            Files.write(
                    path,
                    lines,
                    StandardOpenOption.TRUNCATE_EXISTING
            );

        } catch (IOException e) {
            System.out.println("Error deleting save: " + e.getMessage());
        }
    }


}
