public abstract class Enemy extends Entity {
    private static double difficulty=1;
    private final EnemyType type;

    public Enemy(String name, int maxHp, int damage, EnemyType type) {
        super(name, (int)(maxHp*difficulty), (int)(damage*difficulty));
        this.type=type;
    }

    public EnemyType getType() {return this.type;}

    public double getDifficulty() {return difficulty;}
    public void setDifficulty(double value) {difficulty = value;}
}
