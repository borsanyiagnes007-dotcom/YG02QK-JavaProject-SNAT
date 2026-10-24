public class Player extends Entity implements CanHeal{
    private double defense;
    private static Player instance;

    private Player(String name, int maxHp, int damage, double defense) {
        super(name, maxHp, damage);
        this.defense = defense;
    }

    //Singleton, csak egy player lehet egyszerre
    public static Player getInstance(String name, int maxHp, int damage, double defense) {
        if (instance == null) {
            instance = new Player(name, maxHp, damage, defense);
        }
        return instance;
    }

    //kell egyáltalán?
    public static Player getInstance() {
        return instance;
    }

    //új játékos létrehozása
    public static void newPlayer(String name, int maxHp, int damage, double defense) {
        instance = new Player(name, maxHp, damage, defense);
    }

    public double getDefense() {return defense;}
    public void setDefense(double defense) {this.defense = defense;}

    @Override
    public void heal(int amount) {
        if (amount>=0 && this.isAlive()) {        //csak pozitív irányba lehessen gyógyulni, a 0-t még engedem
            this.setHp(this.getHp() + amount);
            if (this.getHp() > this.getMaxHp()) {this.setHp(this.getMaxHp());}      //ha többre heal-elne, visszaállítom maxra
        }
    }


}
