public class Vampire extends Enemy implements CanHeal{

    public Vampire(String name, int maxHp, int damage) {
        super(name, maxHp, damage, EnemyType.VAMPIRE);        //nem kell külön név, a név "Vampire", mert egy vámpír
    }

    @Override
    public void heal(int amount) {
        if (amount>=0 && this.isAlive()) {
            this.setHp(this.getHp() + (int)(amount*this.getDifficulty()));
            if (this.getHp() > this.getMaxHp()) {this.setHp(this.getMaxHp());}
        }
    }
}
