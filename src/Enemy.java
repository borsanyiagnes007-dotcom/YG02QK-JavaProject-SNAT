public abstract class Enemy extends Entity {
    private static double difficulty=1;

    public Enemy(String name, int maxHp, int damage) {
        super(name, (int)(maxHp*difficulty), (int)(damage*difficulty));
    }

    public double getDifficulty() {return difficulty;}
}
