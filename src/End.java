public class End {

    public static void gameOverScreen(Player player) {
        Misc.screenSpace();
        System.out.println(File.load("textfiles/gameOverScreen.txt"));
        System.out.println("Sadly, you couldn't fight your way through the monsters. Better luck next time.");
        End.finalStats(player);
    }


    public static void gameWinScreen(Player player) {
        Misc.screenSpace();
        System.out.println(File.load("textfiles/gameWinScreen.txt"));
        System.out.println("You defeated all the monsters and now you can rest finally.");
        End.finalStats(player);
    }

    public static void finalStats(Player player) {
        System.out.println("\nEnemies killed: " + (Enemy.getEnemyCount()-1));       //az utolsón kívűl mindegyiket megölte
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
