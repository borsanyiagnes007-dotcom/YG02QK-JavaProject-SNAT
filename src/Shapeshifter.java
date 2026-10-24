public class Shapeshifter extends Enemy{
    private static final double CHANCE_TO_DODGE=0.5;

    public Shapeshifter(String name, int maxHp, int damage) {
        super(name, maxHp, damage, EnemyType.SHAPESHIFTER);
    }

    public double getChanceToDodge() {return CHANCE_TO_DODGE;}

}
