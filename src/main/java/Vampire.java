public class Vampire extends Enemy implements CanHeal{
    private static final double CHANCE_TO_DRINK=0.5;
    private static final int AMOUNT_HEALED=7;

    public Vampire(String name, int maxHp, int damage) {
        super(name, maxHp, damage, EnemyType.VAMPIRE);
    }

    @Override
    public void heal(int amount) {
        if (amount>=0 && this.isAlive()) {
            this.setHp(this.getHp() + (int)(amount*Game.getDifficulty()));
            if (this.getHp() > this.getMaxHp()) {this.setHp(this.getMaxHp());}
            System.out.println(this.getName() + " heals itself!");
        }
    }
    public double getChanceToDrink() {return CHANCE_TO_DRINK;}

    public int getAmountHealed() {return AMOUNT_HEALED;}

    @Override
    public OutCome fight(Player player) {
        return Fight.fight(player, this);
    }
}
