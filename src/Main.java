

public class Main {
    public static void main(String[] args) {

        Start.startScreen();
        Player player = Start.initializePlayer();
        Fight.wave(player, false);

    }
}