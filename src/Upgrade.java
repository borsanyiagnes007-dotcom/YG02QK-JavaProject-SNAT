import java.util.ArrayList;
import java.util.List;

public class Upgrade {
    public static Input input = new Input();

    public static boolean upgradeHall(Player player) {      //boolean, ez false, ha kilépünk az upgrade-ből
        Misc.screenSpace();
        System.out.println("Here are your stats: ");
        player.getStats();
        System.out.println("Here's what you can upgrade:\n");
        //ezt valahogy általánosítani, hogy több helyre lehessen? Esetleg külön osztályba szervezni?
        System.out.println("Skill name:\t\tDefense\t\tHealth\t\tDamage");
        System.out.println("Your level:\t\t" + checkLevel(player.getDefenseLevel(), player.getMaxDefenseLevel()) + " LVL\t\t" + checkLevel(player.getHealthLevel(), player.getMaxHealthLevel()) + " LVL\t\t" + checkLevel(player.getDamageLevel(), player.getMaxDamageLevel()) + " LVL");
        System.out.println("Skill price:\t\t" + player.getBASE_COIN_COST()*(player.getDefenseLevel()+1) + " coins\t\t" + player.getBASE_COIN_COST()*(player.getHealthLevel()+1) + " coins\t\t" + player.getBASE_COIN_COST()*(player.getDamageLevel()+1) + " coins");
        System.out.println();
        ArrayList<String> options = new ArrayList<>(List.of("defense", "health", "damage", "exit"));
        String choice = input.getInput("What would you like to upgrade?", options);
        if (choice.equals("defense")) {
            player.upgradeDefense();
        } else if (choice.equals("health")) {
            player.upgradeHealth();
        } else if (choice.equals("damage")) {
            player.upgradeDamage();
        } else {
            return false;
        }
        return true;
    }

    //???????????????? Most nem megy a gondolkodás Az kéne, hogy upgrade ha lvl 10, akkor max-ot ír, a pénz pedig ne jelenjen?
    public static String checkLevel(int amount, int maxAmount) {
        if (amount >= maxAmount) {
            return "MAX";
        } else {
            return String.valueOf(amount);
        }
    }

}
