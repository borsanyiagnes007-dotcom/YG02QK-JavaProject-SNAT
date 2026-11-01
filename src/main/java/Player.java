
public class Player extends Entity implements CanHeal{
    private static Player instance;
    private int coins;
    private boolean isEating;
    private boolean useSalt;
    private boolean increasedDamage;
    private final int BASE_COIN_COST=3;

    private int defenseLevel=0;
    private int healthLevel=0;
    private int damageLevel=0;
    private final static int MAX_DEFENSE_LEVEL=10;
    private final static int MAX_HEALTH_LEVEL=10;
    private final static int MAX_DAMAGE_LEVEL=10;


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
    public static Player newPlayer(String name, int maxHp, int damage, double defense) {
        instance = new Player(name, maxHp, damage, defense);
        return instance;
    }



    public int getCoins(){return coins;}
    public void setCoins(int coins) {
        if (coins < 0) {
            this.coins=0;
        } else {
        this.coins = coins;}
        }
    public void addCoins(int coins){this.coins += coins;}
    public boolean isEating(){return isEating;}
    public void setEating(boolean isEating){this.isEating = isEating;}
    public boolean isUseSalt(){return useSalt;}
    public void setUseSalt(boolean useSalt){this.useSalt = useSalt;}
    public boolean getIncreasedDamage(){
        return increasedDamage;
    }
    public void setIncreasedDamage(boolean increasedDamage){ this.increasedDamage = increasedDamage; }

    // for upgrades ***********************************************************************************
    public int getBASE_COIN_COST(){return BASE_COIN_COST;}
    public  int getDamageLevel(){return damageLevel;}
    public boolean raiseDamageLevel(){
        if (this.damageLevel>=MAX_DAMAGE_LEVEL){
            return false;
        }
        this.damageLevel++;
        return true;
    }
    public void setDamageLevel(int damageLevel) {
        if (damageLevel < 0) {
            this.damageLevel = 0;
        } else if (damageLevel > MAX_DAMAGE_LEVEL) {
            this.damageLevel = MAX_DAMAGE_LEVEL;
        } else {
            this.damageLevel = damageLevel;
        }
    }
    public int getHealthLevel(){return healthLevel;}
    public void setHealthLevel(int healthLevel) {
        if (healthLevel < 0) {
            this.healthLevel = 0;
        } else if (healthLevel > MAX_HEALTH_LEVEL) {
            this.healthLevel = MAX_HEALTH_LEVEL;
        } else {
            this.healthLevel = healthLevel;
        }
    }
    public boolean raiseHealthLevel(){
        if (this.healthLevel>=MAX_HEALTH_LEVEL){
            return false;
        }
        this.healthLevel++;
        return true;
    }

    public int getDefenseLevel(){return defenseLevel;}
    public void setDefenseLevel(int defenseLevel) {
        if (defenseLevel < 0) {
            this.defenseLevel = 0;
        } else if (defenseLevel > MAX_DEFENSE_LEVEL) {
            this.defenseLevel = MAX_DEFENSE_LEVEL;
        } else {
            this.defenseLevel = defenseLevel;
        }
    }
    public boolean raiseDefenseLevel(){
        if (this.defenseLevel>=MAX_DEFENSE_LEVEL){
            return false;
        }
        this.defenseLevel++;
        return true;
    }

    public int getMaxDefenseLevel(){return MAX_DEFENSE_LEVEL;}
    public int getMaxHealthLevel(){return MAX_HEALTH_LEVEL;}
    public int getMaxDamageLevel(){return MAX_DAMAGE_LEVEL;}


    public boolean upgradeDefense() {      //each level raises defense in a linear way until max defense raised
        if (this.coins>=this.BASE_COIN_COST*(this.defenseLevel+1)) {
            if (this.raiseDefenseLevel()) {
                this.setCoins(this.getCoins() - this.BASE_COIN_COST*this.defenseLevel);
                this.setDefense(this.getMaxDefense()/this.getMaxDefenseLevel()*this.getDefenseLevel());
                return true;
            } else {
                System.out.println("Max defense level reached!");
                return false;
            }
        } else {
            System.out.println("Not enough coins!");
            return false;
        }
    }

    public boolean upgradeHealth() {       //each level adds 50 hp to your health
        if (this.coins>=this.BASE_COIN_COST*(this.healthLevel+1)) {
            if (this.raiseHealthLevel()) {
                this.setCoins(this.getCoins() - this.BASE_COIN_COST*this.healthLevel);
                this.setMaxHp(this.getMaxHp() + 50);
                this.setHp(this.getHp()+50);
                return true;
            } else {
                System.out.println("Max health level reached!");
                return false;
            }
        } else {
            System.out.println("Not enough coins!");
            return false;
        }
    }

    public boolean upgradeDamage() {
        if (this.coins>=this.BASE_COIN_COST*(this.damageLevel+1)) {
            if (this.raiseDamageLevel()) {
                this.setCoins(this.getCoins() - this.BASE_COIN_COST*this.damageLevel);
                this.setDamage(this.getDamage() + 10);
                return true;
            } else {
                System.out.println("Max damage level reached!");
                return false;
            }
        } else {
            System.out.println("Not enough coins!");
            return false;
        }
    }



    //**********************************************************************************

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


    }
}
