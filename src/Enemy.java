public abstract class Enemy extends Entity {
    private final EnemyType type;
    private static int EnemyCount=0;
    private final int ID;

    public Enemy(String name, int maxHp, int damage, EnemyType type) {
        super(name, (int)(maxHp*Game.getDifficulty()), (int)(damage*Game.getDifficulty()), 0);      //Monsters don't have defense, but they could.
        this.type=type;
        EnemyCount++;
        this.ID=EnemyCount;
    }

    public EnemyType getType() {return this.type;}
    public int getID() {return this.ID;}

    public void getStats() {
        System.out.println("Name: " + this.getName());
        System.out.println("Health: " + this.getHp() + "/" + this.getMaxHp());
        System.out.println("Attack damage: " + this.getDamage());
        System.out.println(Misc.getDescription(this));
    }

    public abstract OutCome fight(Player player);

}
