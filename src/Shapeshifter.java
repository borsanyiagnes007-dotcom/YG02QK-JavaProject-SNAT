public class Shapeshifter extends Enemy{
    private static final double CHANCE_TO_DODGE=0.5;

    public Shapeshifter(String name, int maxHp, int damage) {
        super(name, maxHp, damage, EnemyType.SHAPESHIFTER);
    }

    public double getChanceToDodge() {return CHANCE_TO_DODGE;}

    @Override
    public OutCome fight(Player player) {   //azért nem itt specifikálom, hogy a különböző harctípusok egy helyen, a Fight osztályban legyenek
        return Fight.fight(player, this);
    }
}
