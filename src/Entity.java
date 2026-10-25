public abstract class Entity {
    private final String name;
    private int hp;
    private int maxHp;
    private int damage;
    private double defense;

    public Entity(String name, int maxHp, int damage, double defense) {
        this.name = name;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.damage = damage;
        this.defense = defense;
    }

    public String getName() {return name;}
    public int getHp() {return hp;}
    public void setHp(int hp) {this.hp = hp;}
    public int getMaxHp() {return maxHp;}
    public void setMaxHp(int maxHp) {this.maxHp = maxHp;}
    public int getDamage() {return damage;}
    public void setDamage(int damage) {this.damage = damage;}
    public double getDefense() {return defense;}
    public void setDefense(double defense) {this.defense = defense;}

    public int attack(Entity target) {
        target.setHp(target.getHp() - (int) (this.getDamage()-this.getDamage()*target.getDefense()));
        return (int) (this.getDamage()-this.getDamage()*target.getDefense());
    }

    public void takeDamage(int amount) {
        if (this.isAlive()) {this.setHp(this.getHp() - amount);}
    }

    //no heal because not everyone can heal, so interface

    public boolean isAlive() {
        if (this.hp <= 0) {return false;}
        return true;
    }

    public abstract void getStats();    //need to specify for Player and Enemy

}
