import java.util.ArrayList;
import java.util.List;

public class Upgrade {
    public static Input input = new Input();

    public static boolean upgradeHall(Player player) {      //boolean, ez false, ha kilépünk az upgrade-ből
        Misc.screenSpace();
        System.out.println("Welcome to the upgrade hall! You can upgrade your stats here. Type 'exit' to finish upgrading and return to your next wave.\n");
        System.out.println("Here are your stats: ");
        player.getStats();
        System.out.println("Here's what you can upgrade:\n");
        System.out.println();

        System.out.println("Skill\t\tYour level\t\tPrice");
        System.out.println("Defense\t\t" + checkLevel(player.getDefenseLevel(), player.getMaxDefenseLevel()) + " LVL\t\t" + player.getBASE_COIN_COST()*(player.getDefenseLevel()+1) + " coins");
        System.out.println("Health\t\t" + checkLevel(player.getHealthLevel(), player.getMaxHealthLevel()) + " LVL\t\t" +  player.getBASE_COIN_COST()*(player.getHealthLevel()+1) + " coins");
        System.out.println("Damage\t\t" + checkLevel(player.getDamageLevel(), player.getMaxDamageLevel()) + " LVL\t\t" +  player.getBASE_COIN_COST()*(player.getDamageLevel()+1) + " coins");
        System.out.println();

        ArrayList<String> options = new ArrayList<>(List.of("defense", "health", "damage", "exit"));
        String choice = input.getInput("What would you like to upgrade?", options);
        Misc.screenSpace();
        if (choice.equals("defense")) {
            if (!player.upgradeDefense()) {
                input.getInput("Press enter to return to upgrade hall...");
            }
        } else if (choice.equals("health")) {
            if (!player.upgradeHealth()) {
                input.getInput("Press enter to return to upgrade hall...");
            }
        } else if (choice.equals("damage")) {
            if (!player.upgradeDamage()) {

                input.getInput("Press enter to return to upgrade hall...");
            }
        } else {
            return false;
        }
        return true;
    }

    //ha az upgrade elérte a max szintet, ezt azzal jelzem, hogy a szint helyett a "MAX" szöveget tüntetem fel.
    public static String checkLevel(int amount, int maxAmount) {
        if (amount >= maxAmount) {
            return "MAX";
        } else {
            return String.valueOf(amount);
        }
    }

}
