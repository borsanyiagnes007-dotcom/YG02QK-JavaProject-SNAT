public class Shapeshifter extends Enemy{
    private static final double CHANCE_TO_DODGE=0.25;

    public Shapeshifter(String name, int maxHp, int damage) {
        super(name, maxHp, damage, EnemyType.SHAPESHIFTER);
    }

    public double getChanceToDodge() {return CHANCE_TO_DODGE;}

    @Override
    public OutCome fight(Player player) {   //azért nem itt specifikálom, hogy a különböző harctípusok egy helyen, a main.java.Fight osztályban legyenek
        //main.java.Misc.screenSpace();
        if (player.isEating() ||  player.isUseSalt()) {
            player.setEating(false);
        } else {
            System.out.println("You attack " + this.getName() + "!");
            System.out.println();
            if (Math.random() < this.getChanceToDodge()) {
                System.out.println(this.getName() + " takes on the appearance of a loved one. Your hands tremble and you miss.");
                player.setIncreasedDamage(false);       //if we miss with salt, then the increased damage is gone too
            }
            else {
                player.attack(this);
            }
        }

        if (!this.isAlive()) { return OutCome.PLAYERWIN; }
        else {
            System.out.println();
            //System.out.println(shapeshifter.getName() + " is attacking!");

            this.attack(player);
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

    @Override
    public void meetText() {
        System.out.println("You stumble upon a shapeshifter!");
    }
}
