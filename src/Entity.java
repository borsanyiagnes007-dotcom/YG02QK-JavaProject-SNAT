public abstract class Entity {
    private final String name;
    private int hp;
    private int maxHp;
    private int damage;

    public Entity(String name, int maxHp, int damage) {
        this.name = name;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.damage = damage;
    }

    public String getName() {return name;}
    public int getHp() {return hp;}
    public void setHp(int hp) {this.hp = hp;}
    public int getMaxHp() {return maxHp;}
    public void setMaxHp(int maxHp) {this.maxHp = maxHp;}
    public int getDamage() {return damage;}
    public void setDamage(int damage) {this.damage = damage;}

    public void attack(Entity target) {     //needs override that has defense
        target.setHp(target.getHp() - this.damage);
    }

    public void takeDamage(int amount) {
        if (this.isAlive()) {this.setHp(this.getHp() - amount);}
    }

    //no heal because not everyone can heal, so interface

    public boolean isAlive() {
        if (this.hp <= 0) {return false;}
        return true;
    }

}
