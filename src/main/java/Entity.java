
public abstract class Entity {
    private final String name;
    private int hp;
    private int maxHp;
    private int damage;
    private double defense;
    private static final double MAX_DEFENSE=0.9;

    public Entity(String name, int maxHp, int damage, double defense) {
        this.name = name;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.damage = damage;
        this.defense = defense;
    }

    public String getName() {return name;}
    public int getHp() {return hp;}
    public void setHp(int hp) {
        if (hp >=0) {
            this.hp = hp;
        } else if (hp < 0) {
            this.hp = 0;
        }
        if (hp > maxHp) {
            this.hp = maxHp;
        }
    }
    public int getMaxHp() {return maxHp;}
    public void setMaxHp(int maxHp) {
        if (maxHp > 0) {
            this.maxHp = maxHp;
        }
    }
    public int getDamage() {return damage;}
    public void setDamage(int damage) {
        if (damage >=0) {
            this.damage = damage;
        }
    }
    public double getDefense() {return defense;}
    public void setDefense(double defense) {
        if (defense >=0) {
            this.defense = defense;
            if (this.defense >this.getMaxDefense()) {        //maximum a sebzés 90 százalákát lehet blokkolni
                this.defense = this.getMaxDefense();
            }
        }

    }
    public double getMaxDefense() {return MAX_DEFENSE;}

    public void attack(Entity target) {
        int amount = (int) (this.getDamage()-this.getDamage()*target.getDefense());
        target.takeDamage(amount);
        System.out.println(this.getName() + " attacks! " + target.getName() + " takes " + amount + " damage!");
    }

    public void takeDamage(int amount) {
        if (amount > 0) {
            if (this.isAlive()) {this.setHp(this.getHp() - amount);}
        }

    }

    public boolean isAlive() {
        if (this.hp <= 0) {return false;}
        return true;
    }

    public abstract void getStats();    //need to specify for Player and Enemy

}
