import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class Start {
    private Input input = new Input();

    public static void startScreen() {
        try {
            String content = Files.readString(Path.of("src","startScreen.txt"));
            System.out.println(content);
        } catch (IOException e) {
            System.out.println("Couldn't load startScreen.txt");
            e.printStackTrace();
        }
        System.out.println("Welcome to the game!\nMonsters do exist in the world. Your job is to hunt them down and get better to face stronger monsters on the road.\nGood Luck!");


    }

}