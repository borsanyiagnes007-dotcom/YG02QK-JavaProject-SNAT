import java.util.List;
import java.util.ArrayList;

public class Start {
    private static Input input = new Input();

    public static void startScreen() {
        Misc.screenSpace();
        System.out.println(File.load("src/main/resources/textfiles/startScreen.txt"));
        System.out.println("Welcome to the game!\nMonsters do exist in the world. Your job is to hunt them down and get better to face stronger monsters on the road.\nGood Luck!");
        input.getInput("Press enter to continue...");

    }

    public static Player initializePlayer() {
        Misc.screenSpace();
        String name="";
        do {
            name= input.getInput("What is your name, brave hero? \n");
        } while (name.isEmpty());        //valami értelmes név legyen, ne lehessen üres

        Player player = Player.getInstance(name, 100, 10, 0);
        System.out.println("Welcome, "+ player.getName() + "! These are your starting stats:");
        player.getStats();
        System.out.println("\nGet ready for the first wave!");
        input.getInput("Press enter to continue...");
        return player;
    }

    public static void tutorial() {
        System.out.println("In this game, you are going to fight your way through monsters. You control your actions by typing in the terminal what you want to do.\nYou can choose from the options in brackets separated by space. For example: ( attack eat )");
        System.out.println("You have to type in your choice exactly as shown.");        //could ignore caseSensitivity
        System.out.println("Between fights you'll have the chance to upgrade your skills to become stronger.");
        ArrayList<String> options = new ArrayList<>(List.of("start", "exit"));
        String start= input.getInput("\nLet's see if you get it! Type in 'start' to start the game or 'exit' to exit the game", options);
        if (start.equals("exit")) {
            System.out.println("Well, then game is over. We'll wait for your return!");
            System.exit(0);
        }
    }
}