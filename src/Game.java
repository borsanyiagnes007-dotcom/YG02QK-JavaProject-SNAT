public class Game {
    private static double difficulty=1;
    private static boolean gameOver = false;
    private static Input input = new Input();

    public static void raiseDifficulty() {
        difficulty=difficulty*1.25;
    }
    public static double getDifficulty() {
        return difficulty;
    }
    public static boolean getGameOver() {
        return gameOver;
    }
    public static void setGameOver(boolean gameOver) {
        Game.gameOver = gameOver;
    }

    public static void gameplay() {
        Start.startScreen();
        Start.tutorial();
        Player player = Start.initializePlayer();

        for (int i = 1; i<=19; i++) {
            Fight.wave(player, false, i);
            if (Game.getGameOver()) {       //ha valahol meghal, akkor kilépünk a ciklusból és gameOver screen-t kap
                break;
            }
            input.getInput("Press enter to continue...");
            System.out.println("Welcome to the upgrade hall! You can upgrade your stats here. Type 'exit' to finish upgrading and return to your next wave.");
            Upgrade.upgradeHall(player);

        }
        if (Game.getGameOver()) {
            End.gameOverScreen(player);
        } else {
            Fight.wave(player, true, -1);
            if (Game.getGameOver()) {
                End.gameOverScreen(player);
            } else {
                End.gameWinScreen(player);
            }
        }

    }

}
