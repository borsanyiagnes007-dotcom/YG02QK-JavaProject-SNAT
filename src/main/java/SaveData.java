//object for saving the game's data

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaveData  implements Comparable<SaveData>{
    private String name;
    private int maxHp;
    private int hp;
    private int damage;
    private double defense;
    private int healthLevel;
    private int damageLevel;
    private int defenseLevel;
    private int coins;
    private double difficulty;
    private int wavesDefeated;
    private int enemyCount;
    private String savedAt;

    public SaveData(Player player) {
        this.name = player.getName();
        this.maxHp = player.getMaxHp();
        this.hp = player.getHp();
        this.damage = player.getDamage();
        this.defense = player.getDefense();
        this.healthLevel = player.getHealthLevel();
        this.damageLevel = player.getDamageLevel();
        this.defenseLevel = player.getDefenseLevel();
        this.coins = player.getCoins();
        this.difficulty = Game.getDifficulty();
        this.wavesDefeated = Game.getWavesDefeated();
        this.enemyCount = Enemy.getEnemyCount();
        this.savedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public SaveData(String line) {
        String[] data = line.split("\t");

        this.name = data[0];
        this.maxHp = Integer.parseInt(data[1]);
        this.hp = Integer.parseInt(data[2]);
        this.damage = Integer.parseInt(data[3]);
        this.defense = Double.parseDouble(data[4]);
        this.healthLevel = Integer.parseInt(data[5]);
        this.damageLevel = Integer.parseInt(data[6]);
        this.defenseLevel = Integer.parseInt(data[7]);
        this.coins = Integer.parseInt(data[8]);
        this.difficulty = Double.parseDouble(data[9]);
        this.wavesDefeated = Integer.parseInt(data[10]);
        this.enemyCount = Integer.parseInt(data[11]);
        this.savedAt = data[12];
    }

    public String getName(){return this.name;}
    public int getMaxHp(){return this.maxHp;}
    public int getHp(){return this.hp;}
    public int getDamage(){return this.damage;}
    public double getDefense(){return this.defense;}
    public int getHealthLevel(){return this.healthLevel;}
    public int getDamageLevel(){return this.damageLevel;}
    public int getDefenseLevel(){return this.defenseLevel;}
    public int getCoins(){return this.coins;}
    public double getDifficulty(){return this.difficulty;}
    public int getWavesDefeated(){return this.wavesDefeated;}
    public int getEnemyCount() {return this.enemyCount;}


    public String toFileString() {
        return name + "\t" +
                maxHp + "\t" +
                hp + "\t" +
                damage + "\t" +
                defense + "\t" +
                healthLevel + "\t" +
                damageLevel + "\t" +
                defenseLevel + "\t" +
                coins + "\t" +
                difficulty + "\t" +
                wavesDefeated + "\t" +
                enemyCount + "\t" +
                savedAt;
    }

    public void print() {
        System.out.println("Name: " + name);
        System.out.println("HP: " + hp + "/" + maxHp);
        System.out.println("Damage: " + damage);
        System.out.println("Defense: " + defense);
        System.out.println("Health level: " + healthLevel);
        System.out.println("Damage level: " + damageLevel);
        System.out.println("Defense level: " + defenseLevel);
        System.out.println("Coins: " + coins);
        System.out.println("Difficulty: " + difficulty);
        System.out.println("Waves defeated: " + wavesDefeated);
        System.out.println("Enemies defeated: " + enemyCount);
    }

    public Player createPlayer() {      //egy save betöltése
        Player player = Player.newPlayer(this.name, this.maxHp, this.damage, this.defense);

        player.setHp(this.hp);
        player.setHealthLevel(this.healthLevel);
        player.setDamageLevel(this.damageLevel);
        player.setDefenseLevel(this.defenseLevel);
        player.setCoins(this.coins);

        Game.setDifficulty(this.difficulty);
        Game.setWavesDefeated(this.wavesDefeated);
        Enemy.setEnemyCount(this.enemyCount);

        return player;
    }

    public void printSummary(int index) {
        System.out.println(index + ". " + name);
        System.out.println("   HP: " + hp + "/" + maxHp);
        System.out.println("   Waves defeated: " + wavesDefeated);
        System.out.println("   Saved at: " + savedAt);
    }


    @Override       //nem használom, de lehetne
    public int compareTo(SaveData other) {      //a mentéseket a teljesített hullámok száma szerint szeretném sorrendbe rakni
        return Integer.compare(other.wavesDefeated, this.wavesDefeated);
    }
}
