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
        return Fight.fight(player, this);
    }
}
