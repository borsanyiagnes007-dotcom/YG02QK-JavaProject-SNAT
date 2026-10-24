public class Demon extends Enemy{
    private static final double CHANCE_TO_POSSES=0.5;

    public Demon(int maxHp, int damage) {
        super("Demon", maxHp, damage);
    }

    public double getChanceToPossess() {return CHANCE_TO_POSSES;}

}
