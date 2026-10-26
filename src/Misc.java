public class Misc {
    public static void screenSpace() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");       //"clearing" the screen - don't want to actually clear the screen, so it can be scrolled back.
    }

    public static String getDescription(Enemy enemy) {
        if (enemy.getType()==EnemyType.DEMON) {
            return "Description: A demon is an evil creature that likes to possess its prey and hurt them.";
        }
        if (enemy.getType()==EnemyType.VAMPIRE) {
            return "Description: A vampire drinks their opponent's blood, healing itself in the process.";
        }
        if (enemy.getType()==EnemyType.SHAPESHIFTER) {
            return "Description: A shapeshifter is a ferocious monster, capable to take on the appearance of loved ones and confuse you.";
        }
        if (enemy.getType()==EnemyType.LEVIATHAN) {
            return "Description: Powerful ancient creature. We don't know much about them.";
        }
        return "";

    }

}
