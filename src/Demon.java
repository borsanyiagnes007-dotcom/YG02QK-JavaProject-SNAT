public class Demon extends Enemy{
    private static final double CHANCE_TO_POSSES=0.5;


    public Demon(String name, int maxHp, int damage) {
        super(name, maxHp, damage, EnemyType.DEMON);

    }

    public double getChanceToPossess() {return CHANCE_TO_POSSES;}

    @Override
    public OutCome fight(Player player) {
        return Fight.fight(player, this);
    }
}
