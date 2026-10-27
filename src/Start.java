public class Start {
    private static Input input = new Input();

    public static void startScreen() {
        Misc.screenSpace();
        System.out.println(File.load("textfiles/startScreen.txt"));
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
        return player;
    }
}