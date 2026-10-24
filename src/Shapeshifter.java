public class Shapeshifter extends Enemy{
    private static final double CHANCE_TO_DODGE=0.5;

    public Shapeshifter(int maxHp, int damage) {
        super("Shapeshifter", maxHp, damage);
    }

    public double getChanceToDodge() {return CHANCE_TO_DODGE;}

}
