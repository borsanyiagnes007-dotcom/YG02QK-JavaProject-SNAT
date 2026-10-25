public class Fight {

    //overload, mert a harc mechanikája változik az ellenféltől függően
    // a visszatérési érték Outcome típusú, ami megmondja, hogy meghalt-e valamelyik fél és ha igen, melyik.
    public static OutCome fight(Player player, Vampire vampire) {
        Misc.screenSpace();

        System.out.println("You attack " + vampire.getName() + "! It loses " + player.attack(vampire) + " hp!");
        if (!vampire.isAlive()) { return OutCome.PLAYERWIN;}
        else {
            System.out.println(vampire.getName() + " is attacking!");
            if (Math.random() < vampire.getChanceToDrink()) {
                System.out.println(vampire.getName() + " bites you and drinks your blood! It heals!");
                vampire.heal(vampire.getAmountHealed());
            }
            System.out.println("You lose " + vampire.attack(player) + " hp!");
            if (!player.isAlive()) {
                return OutCome.ENEMYWIN;
            }
        }
        return OutCome.NONE;
    }

    public static OutCome fight(Player player, Demon demon) {
        Misc.screenSpace();

        System.out.println("You attack " + demon.getName() + "! It loses " + player.attack(demon) + " hp!");
        if (!demon.isAlive()) { return OutCome.PLAYERWIN; }
        else {
            System.out.println(demon.getName() + " is attacking!");
            if (Math.random() < demon.getChanceToPossess()) {
                System.out.println(demon.getName() + " possesses you! You stab yourself.");
                System.out.println("You lose " + demon.attack(player) + " hp!\nThe demon jumps out of you.");
                if (!player.isAlive()) {return OutCome.ENEMYWIN;}
                else {
                    System.out.println("It attacks again!");
                }
            }
            System.out.println("You lose " + demon.attack(player) + " hp!");
            if (!player.isAlive()) {
                return OutCome.ENEMYWIN;
            }
            return OutCome.NONE;
        }
    }

    public static OutCome fight (Player player, Shapeshifter shapeshifter) {
        Misc.screenSpace();

        System.out.println("You attack " + shapeshifter.getName() + "!");
        if (Math.random() < shapeshifter.getChanceToDodge()) {
            System.out.println(shapeshifter.getName() + " takes on the appearance of a loved one. Your hands tremble and you miss.");
        }
        else {
            System.out.println("It loses " + player.attack(shapeshifter) + " hp!");
        }
        if (!shapeshifter.isAlive()) { return OutCome.PLAYERWIN; }
        else {
            System.out.println(shapeshifter.getName() + " is attacking!");
            System.out.println("You lose " + shapeshifter.attack(player) + " hp!");
            if (!player.isAlive()) {
                return OutCome.ENEMYWIN;
            }
            return OutCome.NONE;
        }
    }

    public static OutCome fight (Player player, Leviathan leviathan) {
        Misc.screenSpace();

        System.out.println("You attack " + leviathan.getName() + "! It loses " + player.attack(leviathan) + " hp!");
        if (!leviathan.isAlive()) { return OutCome.PLAYERWIN; }
        else {
            if (Math.random() < leviathan.getChanceToDoublehit()) {
                System.out.println("Huge black tentacles spread from " + leviathan.getName() + ". Look out! They are coming from every direction!");
                for (int i=0; i<(int)(Math.random()*2)+1;i++) {
                    System.out.println(leviathan.getName() + " is attacking! You lose" + leviathan.attack(player) + " hp!");
                    if (!player.isAlive()) {return OutCome.ENEMYWIN;}
                }
            }
            System.out.println(leviathan.getName() + " is attacking! You lose" + leviathan.attack(player) + " hp!");
            if (!player.isAlive()) {return OutCome.ENEMYWIN;}
            System.out.println(leviathan.getName() + " is healing!");
            leviathan.heal(leviathan.getAmountHealed());
            return OutCome.NONE;
        }
    }
}
