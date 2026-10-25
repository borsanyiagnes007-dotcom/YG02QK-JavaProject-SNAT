public class Vampire extends Enemy implements CanHeal{
    private static final double CHANCE_TO_DRINK=0.5;
    private static final int AMOUNT_HEALED=7;

    public Vampire(String name, int maxHp, int damage) {
        super(name, maxHp, damage, EnemyType.VAMPIRE);        //nem kell külön név, a név "Vampire", mert egy vámpír
    }

    //ide kell a véletlen generátor?
    @Override
    public void heal(int amount) {
        if (amount>=0 && this.isAlive()) {
            this.setHp(this.getHp() + (int)(amount*this.getDifficulty()));
            if (this.getHp() > this.getMaxHp()) {this.setHp(this.getMaxHp());}
            System.out.println(this.getName() + " heals itself!");
        }
    }
    public double getChanceToDrink() {return CHANCE_TO_DRINK;}
    public int getAmountHealed() {return AMOUNT_HEALED;}
}
