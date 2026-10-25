public class Player extends Entity implements CanHeal{
    private static Player instance;
    private int coins;

    private Player(String name, int maxHp, int damage, double defense) {
        super(name, maxHp, damage, defense);
        this.coins= 0;      //kezdetben nincs pénze
    }

    //Singleton, csak egy player lehet egyszerre
    public static Player getInstance(String name, int maxHp, int damage, double defense) {
        if (instance == null) {
            instance = new Player(name, maxHp, damage, defense);
        }
        return instance;
    }

    //új játékos létrehozása
    public static void newPlayer(String name, int maxHp, int damage, double defense) {
        instance = new Player(name, maxHp, damage, defense);
    }

    public int getCoins(){return coins;}
    public void setCoins(int coins) {this.coins = coins;}
    public void addCoins(int coins){this.coins += coins;}

    @Override
    public void heal(int amount) {
        if (amount>=0 && this.isAlive()) {        //csak pozitív irányba lehessen gyógyulni, a 0-t még engedem
            this.setHp(this.getHp() + amount);
            System.out.println(this.getName() + " heals!");
            if (this.getHp() > this.getMaxHp()) {this.setHp(this.getMaxHp());}      //ha többre heal-elne, visszaállítom maxra
        }
    }

    @Override
    public void getStats() {
        System.out.println("Name: " + this.getName());
        System.out.println("Health: " + this.getHp() + "/" + this.getMaxHp());
        System.out.println("Attack damage: " + this.getDamage());
        System.out.println("Defense: " + this.getDefense());
        System.out.println("Coins: " + this.getCoins());
    }
}
