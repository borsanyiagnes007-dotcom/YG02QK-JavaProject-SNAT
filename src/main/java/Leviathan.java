
public class Leviathan extends Enemy implements CanHeal{        //This will be the Boss
    private static final double CHANCE_TO_DOUBLEHIT=0.3;    //has a chance to attack 2 or 3 times instead of one
    private static final int AMOUNT_HEALED=5;

    public Leviathan(String name, int maxHp, int damage) {
        super(name, maxHp, damage, EnemyType.LEVIATHAN);
    }

    public double getChanceToDoublehit() {
        return CHANCE_TO_DOUBLEHIT;
    }
    public int getAmountHealed() {
        return AMOUNT_HEALED;
    }

    @Override
    public void heal(int amount) {      //the less health it has, the more it heals, max 2 times the base amount
        if (this.isAlive()) {
            this.setHp(this.getHp() + (int) (amount*(1+((this.getMaxHp()-this.getHp())/(double)this.getMaxHp()))));
            if (this.getHp()>this.getMaxHp()) {this.setHp(this.getMaxHp());}
        }

    }

    @Override
    public OutCome fight(Player player) {
        //main.java.Misc.screenSpace();
        if (player.isEating() || player.isUseSalt()) {
            player.setEating(false);
        } else {
            player.attack(this);
        }

        System.out.println();
        if (!this.isAlive()) { return OutCome.PLAYERWIN; }
        else {
            if (Math.random() < this.getChanceToDoublehit()) {
                System.out.println("Huge black tentacles spread from " + this.getName() + ". Look out! They are coming from every direction!");
                for (int i=0; i<(int)(Math.random()*2)+1;i++) {
                    this.attack(player);
                    if (!player.isAlive()) {return OutCome.ENEMYWIN;}
                }
            }
            this.attack(player);
            if (!player.isAlive()) {return OutCome.ENEMYWIN;}
            System.out.println(this.getName() + " is healing!");
            this.heal(this.getAmountHealed());
            if (player.isUseSalt()) {
                player.setIncreasedDamage(true);
                player.setUseSalt(false);
            }
            return OutCome.NONE;
        }
    }

    @Override
    public void meetText() {
        System.out.println("You stumble upon a... wait. You haven't seen one like it before. A leviathan!");
    }

    @Override
    public String getDescription() {
        return "Description: Powerful ancient creature. We don't know much about them.";
    }
}
