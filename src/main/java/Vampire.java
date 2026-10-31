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
        //main.java.Misc.screenSpace();
        if (player.isEating() || player.isUseSalt()) {        //ha eszek, nem támadok de engem támadnak
            player.setEating(false);
        } else {
            player.attack(this);
        }
        System.out.println();
        if (!this.isAlive()) { return OutCome.PLAYERWIN;}
        else {
            //System.out.println(vampire.getName() + " is attacking!");
            if (Math.random() < this.getChanceToDrink()) {
                System.out.println(this.getName() + " bites you and drinks your blood! It heals!");
                this.heal(this.getAmountHealed());
            }
            this.attack(player);
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

    @Override
    public void meetText() {
        System.out.println("You stumble upon a vampire!");
    }

}
