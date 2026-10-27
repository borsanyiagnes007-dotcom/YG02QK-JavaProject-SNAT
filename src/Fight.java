import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Fight {
    private static Input input = new Input();

    //overload, mert a harc mechanikája változik az ellenféltől függően
    // a visszatérési érték Outcome típusú, ami megmondja, hogy meghalt-e valamelyik fél és ha igen, melyik.
    public static OutCome fight(Player player, Vampire vampire) {
        //Misc.screenSpace();
        if (player.isEating() || player.isUseSalt()) {        //ha eszek, nem támadok de engem támadnak
            player.setEating(false);
        } else {
            player.attack(vampire);
        }
        System.out.println();
        if (!vampire.isAlive()) { return OutCome.PLAYERWIN;}
        else {
            //System.out.println(vampire.getName() + " is attacking!");
            if (Math.random() < vampire.getChanceToDrink()) {
                System.out.println(vampire.getName() + " bites you and drinks your blood! It heals!");
                vampire.heal(vampire.getAmountHealed());
            }
            vampire.attack(player);
            if (!player.isAlive()) {
                return OutCome.ENEMYWIN;
            }
        }
        if (player.isUseSalt())  {          //átkapcsolom, hogy következő körben támadjon erősen
            player.setIncreasedDamage(true);
            player.setUseSalt(false);
        }
        return OutCome.NONE;
    }

    public static OutCome fight(Player player, Demon demon) {
        //Misc.screenSpace();
        if (player.isEating() || player.isUseSalt()) {
            player.setEating(false);
        } else {
            player.attack(demon);
        }

        System.out.println();
        if (!demon.isAlive()) { return OutCome.PLAYERWIN; }
        else {
            System.out.println(demon.getName() + " is attacking!");
            if (Math.random() < demon.getChanceToPossess()) {
                System.out.println(demon.getName() + " possesses you! You stab yourself.");
                demon.attack(player);
                System.out.println("The demon jumps out of you.");
                if (!player.isAlive()) {return OutCome.ENEMYWIN;}
                else {
                    System.out.println("It attacks again!");
                }
            }
            demon.attack(player);
            if (!player.isAlive()) {
                return OutCome.ENEMYWIN;
            }
            if (player.isUseSalt()) {
                player.setIncreasedDamage(true);
                player.setUseSalt(false);
            }
            return OutCome.NONE;
        }
    }

    public static OutCome fight (Player player, Shapeshifter shapeshifter) {
        //Misc.screenSpace();
        if (player.isEating() ||  player.isUseSalt()) {
            player.setEating(false);
        } else {
            System.out.println("You attack " + shapeshifter.getName() + "!");
        }

        System.out.println();
        if (Math.random() < shapeshifter.getChanceToDodge()) {
            System.out.println(shapeshifter.getName() + " takes on the appearance of a loved one. Your hands tremble and you miss.");
        }
        else {
            player.attack(shapeshifter);
        }
        if (!shapeshifter.isAlive()) { return OutCome.PLAYERWIN; }
        else {
            System.out.println();
            //System.out.println(shapeshifter.getName() + " is attacking!");

            shapeshifter.attack(player);
            if (!player.isAlive()) {
                return OutCome.ENEMYWIN;
            }
            if (player.isUseSalt()) {
                player.setIncreasedDamage(true);
                player.setUseSalt(false);
            }
            return OutCome.NONE;
        }
    }

    public static OutCome fight (Player player, Leviathan leviathan) {
        //Misc.screenSpace();
        if (player.isEating() || player.isUseSalt()) {
            player.setEating(false);
        } else {
            player.attack(leviathan);
        }

        System.out.println();
        if (!leviathan.isAlive()) { return OutCome.PLAYERWIN; }
        else {
            if (Math.random() < leviathan.getChanceToDoublehit()) {
                System.out.println("Huge black tentacles spread from " + leviathan.getName() + ". Look out! They are coming from every direction!");
                for (int i=0; i<(int)(Math.random()*2)+1;i++) {
                    leviathan.attack(player);
                    if (!player.isAlive()) {return OutCome.ENEMYWIN;}
                }
            }
            leviathan.attack(player);
            if (!player.isAlive()) {return OutCome.ENEMYWIN;}
            System.out.println(leviathan.getName() + " is healing!");
            leviathan.heal(leviathan.getAmountHealed());
            return OutCome.NONE;
        }
    }

    public static void wave(Player player, boolean boss) {      //valami nem jó a screenspace-el, mert healelésnél elugrik és attack lesz

        Enemy enemy;
        if (boss) {
            enemy= new Leviathan("Leviathan", 200, 20);
        }
        else {
            int choice = (int) (Math.random() * 3);       //nem lehet harom vagy tobb
            if (choice == 0) {
                enemy = new Vampire("Vampire", 50, 5);        //Could be configured to have it read from a file
                System.out.println("You stumble upon a vampire!");
            } else if (choice == 1) {
                enemy = new Shapeshifter("Shapeshifter", 70, 7);
                System.out.println("You stumble upon a shapeshifter!");
            } else {
                enemy = new Demon("Demon", 80, 6);
                System.out.println("You stumble upon a demon!");
            }
        }

        OutCome win=OutCome.NONE;
        do {
            //Misc.screenSpace();
            System.out.println("Your stats:");
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
            else if (Objects.equals(choice, "eat")) {       //enemy should still attack?
                player.heal(20);
                player.setEating(true);
                //Misc.screenSpace();
                System.out.println("Your hp: " + player.getHp() + "/" + player.getMaxHp());

            }
            else if (Objects.equals(choice, "salt")) {
                player.setUseSalt(true);
                System.out.println(player.getName() + " uses salt! You'll deal increased damage!");
            }

            win=enemy.fight(player);

        } while (win==OutCome.NONE);

        //Misc.screenSpace();
        if (win==OutCome.PLAYERWIN) {
            System.out.println(enemy.getName() + " falls to the ground! Another win for " + player.getName());
            player.addCoins((int) (5*Game.getDifficulty()));
            System.out.println("You obtained " + (int) (5*Game.getDifficulty()) + " coins.");
            Game.raiseDifficulty();
        } else {
            System.out.println("You lose the game."); // instead of this, GameOver screen
            Game.setGameOver(true);

        }



    }
}
