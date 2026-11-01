//Simple Factory design pattern.

public class EnemyFactory {

    public static Enemy createRandomEnemy(boolean boss) {
        if (boss) {
            return new Leviathan("Leviathan", 150, 10);
        }

        int choice = (int)(Math.random()*3);
        return switch (choice) {
            case 0 -> new Vampire("Vampire", 50, 5);
            case 1 -> new Shapeshifter("Shapeshifter", 70, 7);
            default -> new Demon("Demon", 80, 6);
        };
    }
}
