public abstract class Enemy extends Entity {
    private static double difficulty=1;
    private final EnemyType type;
    private static int EnemyCount=0;
    private final int ID;

    public Enemy(String name, int maxHp, int damage, EnemyType type) {
        super(name, (int)(maxHp*difficulty), (int)(damage*difficulty), 0);      //Monsters don't have defense, but they could.
        this.type=type;
        EnemyCount++;
        this.ID=EnemyCount;
    }

    public EnemyType getType() {return this.type;}
    public double getDifficulty() {return difficulty;}
    public void setDifficulty(double value) {difficulty = value;}
    public void raiseDifficulty() {difficulty *= 1.25 ;}
    public int getID() {return this.ID;}

    public void getStats() {
        System.out.println("\nName: " + this.getName());
        System.out.println("Health: " + this.getHp() + "/" + this.getMaxHp());
        System.out.println("Attack damage: " + this.getDamage());
        System.out.println(Misc.getDescription(this));
    }

}
