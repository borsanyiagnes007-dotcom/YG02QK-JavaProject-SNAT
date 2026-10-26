public class Game {
    private static double difficulty=1;
    private static boolean gameOver = false;

    public static void raiseDifficulty() {
        difficulty=difficulty*1.25;
    }
    public static double getDifficulty() {
        return difficulty;
    }
    public static boolean getGameOver() {
        return gameOver;
    }
    public static void setGameOver(boolean gameOver) {
        Game.gameOver = gameOver;
    }
}
