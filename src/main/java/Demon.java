public class Demon extends Enemy {
    private static final double CHANCE_TO_POSSES=0.5;


    public Demon(String name, int maxHp, int damage) {
        super(name, maxHp, damage, EnemyType.DEMON);

    }

    public double getChanceToPossess() {return CHANCE_TO_POSSES;}

    @Override
    public OutCome fight(Player player) {
        //main.java.Misc.screenSpace();
        if (player.isEating() || player.isUseSalt()) {
            player.setEating(false);
        } else {
            player.attack(this);
        }

        System.out.println();
        if (!this.isAlive()) { return OutCome.PLAYERWIN; }
        else {
            System.out.println(this.getName() + " is attacking!");
            if (Math.random() < this.getChanceToPossess()) {
                System.out.println(this.getName() + " possesses you! You stab yourself.");
                this.attack(player);
                System.out.println("The demon jumps out of you.");
                if (!player.isAlive()) {
                    return OutCome.ENEMYWIN;
                } else {
                    System.out.println("It attacks again!");
                }
            }
            this.attack(player);
            if (!player.isAlive()) {
                return OutCome.ENEMYWIN;
            }
            if (player.isUseSalt()) {
                player.setIncreasedDamage(true);
                player.setUseSalt(false);
            }
            return OutCome.NONE;
        }

    }

    @Override
    public void meetText() {
        System.out.println("You stumble upon a demon!");
    }

    @Override
    public String getDescription() {
        return "Description: A demon is an evil creature that likes to possess its prey and hurt them.";
    }
}
