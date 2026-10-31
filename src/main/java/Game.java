import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private static double difficulty=1;
    private static boolean gameOver = false;
    private static Input input = new Input();
    private static int wavesDefeated=0;
    private static String saveTo="saves/saves.txt";

    public static void raiseDifficulty() {
        difficulty=difficulty*1.2;
    }
    public static double getDifficulty() {
        return difficulty;
    }
    public static void setDifficulty(double difficulty) {Game.difficulty=difficulty;}
    public static boolean getGameOver() {
        return gameOver;
    }
    public static void setGameOver(boolean gameOver) {
        Game.gameOver = gameOver;
    }
    public static void waveWon() {
        wavesDefeated++;
    }
    public static int getWavesDefeated() {
        return wavesDefeated;
    }
    public static void resetWaves() {
        wavesDefeated=0;
    }
    public static void setWavesDefeated(int set) {wavesDefeated = set;}
    public static String getSaveTo() {return saveTo;}

    public static void gameplay() {
        Start.startScreen();
        Start.tutorial();
        SaveData.updateNextId(Game.getSaveTo());
        Player player = null;
        List<String> saves = File.readLines(Game.getSaveTo());
        boolean newGame = true;
        if (!saves.isEmpty()) {
            ArrayList<String> options = new ArrayList<>(List.of("saves", "new"));
            do {
                Misc.screenSpace();
                String choice = input.getInput("Would you like to load or edit a save or start a new game?", options);
                if (choice.equals("saves")) {
                    player = Saves.loadGameScreen(saves);
                    if (player == null) {       //visszamenjek az új játék lehetőséghez
                        newGame = false;
                    } else {
                        newGame = false;
                        break;
                    }
                } else {
                    newGame = true;
                }
            } while (!newGame);
        }
        if (newGame){
            player = Start.initializePlayer();
        }

        while (true) {
            if (!newGame) {     //ha töltött játék, egyszer még a shop-ban kezdjen és onnan menjen harcolni
                while (Upgrade.upgradeHall(player)){}
                newGame=true;       // ezt utána már nem használom, tehát csak ennek a kiküszöbölésére használom
            }
            while (Game.getWavesDefeated()<9) {            // 9 waves + last boss wave
                Fight.wave(player, false, Game.getWavesDefeated()+1);
                if (Game.getGameOver()) {       //ha valahol meghal, akkor kilépünk a ciklusból és gameOver screen-t kap
                    break;
                }

                String wait = input.getInput("Press enter to continue...");
                player.setUseSalt(false);       //ha esetleg volt só, akkor ne menjen át következő körre
                while (Upgrade.upgradeHall(player)) {}

            }
            if (Game.getGameOver()) {
                End.gameOverScreen(player);
            } else {
                Fight.wave(player, true, -1);       // the boss fight, last wave
                if (Game.getGameOver()) {
                    End.gameOverScreen(player);
                } else {
                    End.gameWinScreen(player);
                }
            }
            ArrayList<String> options = new ArrayList<>(List.of("reset", "exit"));
            String reset= input.getInput("Would you like to play again, or close the game?", options);
            if (reset.equals("reset")) {        //try again, this means everything resets except the name
                player = Game.resetGame(player);
            } else {
                System.out.println("Good bye!");
                System.exit(0);
            }
        }
    }

    public static Player resetGame(Player player) {         //Facade?
        Game.setDifficulty(1);
        gameOver=false;
        Game.resetWaves();
        System.out.println("Welcome, " + player.getName() + "! Get ready for the first wave!");
        input.getInput("Press enter to continue...");
        return Player.newPlayer(player.getName(), 100, 10,0);       //new player with the old one's name
    }

}
