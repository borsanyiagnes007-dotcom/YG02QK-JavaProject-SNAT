public class Player extends Entity implements CanHeal{
    private static Player instance;
    private int coins;
    private boolean isEating;
    private boolean useSalt;
    private boolean increasedDamage;


    private Player(String name, int maxHp, int damage, double defense) {
        super(name, maxHp, damage, defense);
        this.coins= 0;      //kezdetben nincs pénze
        this.isEating = false;
        this.useSalt = false;
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
    public boolean isEating(){return isEating;}
    public void setEating(boolean isEating){this.isEating = isEating;}
    public boolean isUseSalt(){return useSalt;}
    public void setUseSalt(boolean useSalt){this.useSalt = useSalt;}
    public boolean getIncreasedDamage(){
        return increasedDamage;
    }
    public void setIncreasedDamage(boolean increasedDamage){ this.increasedDamage = increasedDamage; }



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

    @Override
    public void attack(Entity target) {
        int amount=(int) ((this.getDamage()-this.getDamage()*target.getDefense()));
        if (this.increasedDamage) {
            amount= (int) (amount*2.5);
            this.increasedDamage=false;
        }
        target.takeDamage(amount);
        System.out.println(this.getName() + " attacks! " + target.getName() + " takes " + amount + " damage!");

        /*
        if (useSalt) {
            lose= (int) (lose*2.5);
            useSalt=false;
        }
        */

    }
}
