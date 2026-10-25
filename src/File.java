import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

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
}
