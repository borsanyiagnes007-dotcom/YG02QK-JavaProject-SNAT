
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Fight {
    private static Input input = new Input();

    public static void wave(Player player, boolean boss, int waveCount) {

        Enemy enemy;
        Misc.screenSpace();
        if (waveCount == -1) {
            System.out.println("Final wave\n");
        } else {
            System.out.println("Wave " + waveCount + "\n");
        }

        enemy=EnemyFactory.createRandomEnemy(boss);
        enemy.meetText();

        OutCome win=OutCome.NONE;
        do {
            System.out.println("\nYour stats:");
            player.getStats();
            System.out.println("\nYour enemy's stats:");
            enemy.getStats();
            System.out.println();
            ArrayList<String> options = new ArrayList<>(List.of("attack", "eat", "salt"));
            String choice = input.getInput("What are you going to do?", options);
            Misc.screenSpace();
            if (Objects.equals(choice, "attack")) {
                //nothing because this is the standard
            }
            else if (Objects.equals(choice, "eat")) {
                player.heal((int)(player.getMaxHp()/4));
                player.setEating(true);

                System.out.println("Your hp: " + player.getHp() + "/" + player.getMaxHp());

            }
            else if (Objects.equals(choice, "salt")) {
                player.setUseSalt(true);
                System.out.println(player.getName() + " uses salt! You'll deal increased damage!");
            }

            win=enemy.fight(player);

        } while (win==OutCome.NONE);

        if (win==OutCome.PLAYERWIN) {
            Game.waveWon();
            System.out.println();
            System.out.println(enemy.getName() + " falls to the ground! Another win for " + player.getName() + "!");
            player.addCoins((int) (6*Game.getDifficulty()));
            System.out.println("You obtained " + (int) (6*Game.getDifficulty()) + " coins.");
            Game.raiseDifficulty();
        } else {
            Game.setGameOver(true);

        }
    }
}
