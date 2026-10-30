import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Game {
    private static double difficulty=1;
    private static boolean gameOver = false;
    private static Input input = new Input();
    private static int wavesDefeated=0;

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

    public static void gameplay() {
        Start.startScreen();
        Start.tutorial();
        Player player = null;
        List<String> saves = File.readLines("textfiles/saves.txt");
        boolean newGame = true;
        if (!saves.isEmpty()) {
            ArrayList<String> options = new ArrayList<>(List.of("saves", "new"));
            do {
                Misc.screenSpace();
                String choice = input.getInput("Would you like to load or edit a save or start a new game?", options);
                if (choice.equals("saves")) {
                    player = Game.loadGameScreen(saves);
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
        difficulty=1;
        gameOver=false;
        Game.resetWaves();
        System.out.println("Welcome, " + player.getName() + "! Get ready for the first wave!");
        input.getInput("Press enter to continue...");
        return Player.newPlayer(player.getName(), 100, 10,0);       //new player with the old one's name
    }


    public static void saveAndExit(Player player) {
        SaveData data = new SaveData(player);
        Misc.screenSpace();
        File.addLine("textfiles/saves.txt", data.toFileString());
        System.out.println("Your save file has been saved! Good bye!");
        System.exit(0);
    }


    public static Player loadGameScreen(List<String> saves) {       //loading and deleting saves
        while (true) {
            Misc.screenSpace();
            List<SaveData> saveDataList = new ArrayList<>();

            for (String line : saves) {
                saveDataList.add(new SaveData(line));
            }
            //Collections.sort(saveDataList);         // automatikusan a compareTo() metódust használja

            System.out.println("========== SAVED GAMES ==========");

            for (int i = 0; i < saveDataList.size(); i++) {
                saveDataList.get(i).printSummary(i + 1);
                System.out.println("----------------------------");
            }

            if (saveDataList.isEmpty()) {
                System.out.println("There are no saves!");
                input.getInput("Press enter to go back...");
                return null;
            }

            ArrayList<String> options = new ArrayList<>();

            for (int i = 1; i <= saveDataList.size(); i++) {
                options.add(String.valueOf(i));
            }

            options.add("delete");
            options.add("back");

            String choice = input.getInput("Choose a save to load, or type 'delete' to delete a save:", options);

            if (choice.equals("back")) {
                return null;
            }

            if (choice.equals("delete")) {
                ArrayList<String> deleteOptions = new ArrayList<>();
                for (String option : options) {
                    if (!option.equals("delete") && !option.equals("back")) {
                        deleteOptions.add(option);
                    }
                }

                String deleteChoice = input.getInput("Which save do you want to delete?", deleteOptions);

                int index = Integer.parseInt(deleteChoice) - 1;

                File.deleteLine("/textfiles/saves.txt", index);
                saves.remove(index);

                System.out.println("Save deleted!");
                input.getInput("Press enter to continue...");

            } else {
                int index = Integer.parseInt(choice) - 1;

                return saveDataList.get(index).createPlayer();
            }
        }
    }
}
