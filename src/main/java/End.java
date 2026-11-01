
public class End {

    public static void gameOverScreen(Player player) {
        Misc.screenSpace();
        System.out.println(File.load("src/main/resources/textfiles/gameOverScreen.txt"));
        System.out.println("Sadly, you couldn't fight your way through the monsters. Better luck next time.");
        System.out.println("Thanks for playing!");
        End.finalStats(player);
    }


    public static void gameWinScreen(Player player) {
        Misc.screenSpace();
        System.out.println(File.load("src/main/resources/textfiles/gameWinScreen.txt"));
        System.out.println("You defeated all the monsters and now you can rest finally.");
        System.out.println("Thanks for playing!");
        End.finalStats(player);
    }

    public static void finalStats(Player player) {
        System.out.println("\nEnemies killed: " + Game.getWavesDefeated());


        //could be overall taken damage or something?
        System.out.println("Your last statistics:");
        System.out.println("Max hp: " + player.getMaxHp());
        System.out.println("Damage: " + player.getDamage());
        System.out.println("Defense: " + player.getDefense());

        System.out.println("\nHealth level: " + player.getHealthLevel());
        System.out.println("Damage level: " + player.getDamageLevel());
        System.out.println("Defense level: " + player.getDefenseLevel());
    }
}
