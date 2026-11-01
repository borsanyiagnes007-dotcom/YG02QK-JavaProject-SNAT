classDiagram
direction LR

class Entity {
  <<abstract>>
  -String name
  -int hp
  -int maxHp
  -int damage
  -double defense
  -double MAX\_DEFENSE$
  +Entity(name, maxHp, damage, defense)
  +getName() String
  +getHp() int
  +setHp(hp) void
  +getMaxHp() int
  +setMaxHp(maxHp) void
  +getDamage() int
  +setDamage(damage) void
  +getDefense() double
  +setDefense(defense) void
  +getMaxDefense() double
  +attack(target) void
  +takeDamage(amount) void
  +isAlive() boolean
  +getStats() void
}

class Enemy {
  <<abstract>>
  -EnemyType type
  -int EnemyCount$
  -int ID
  +Enemy(name, maxHp, damage, type)
  +getType() EnemyType
  +getID() int
  +setEnemyCount(set)$ void
  +getEnemyCount()$ int
  +resetEnemyCount()$ void
  +getStats() void
  +fight(player) OutCome
  +meetText() void
  +getDescription() String
}

class Player {
  -Player instance$
  -int coins
  -boolean isEating
  -boolean useSalt
  -boolean increasedDamage
  -int defenseLevel
  -int healthLevel
  -int damageLevel
  -Player(name, maxHp, damage, defense)
  +getInstance(...)$ Player
  +newPlayer(...)$ Player
  +addCoins(coins) void
  +upgradeDefense() boolean
  +upgradeHealth() boolean
  +upgradeDamage() boolean
  +heal(amount) void
  +attack(target) void
  +getStats() void
}

class CanHeal {
  <<interface>>
  +heal(amount) void
}

class Demon {
  -double CHANCE\_TO\_POSSESS$
  +Demon(name, maxHp, damage)
  +getChanceToPossess() double
  +fight(player) OutCome
  +meetText() void
  +getDescription() String
}

class Vampire {
  -double CHANCE\_TO\_DRINK$
  -int AMOUNT\_HEALED$
  +Vampire(name, maxHp, damage)
  +heal(amount) void
  +getChanceToDrink() double
  +getAmountHealed() int
  +fight(player) OutCome
  +meetText() void
  +getDescription() String
}

class Shapeshifter {
  -double CHANCE\_TO\_DODGE$
  +Shapeshifter(name, maxHp, damage)
  +getChanceToDodge() double
  +fight(player) OutCome
  +meetText() void
  +getDescription() String
}

class Leviathan {
  -double CHANCE\_TO\_DOUBLEHIT$
  -int AMOUNT\_HEALED$
  +Leviathan(name, maxHp, damage)
  +heal(amount) void
  +getChanceToDoublehit() double
  +getAmountHealed() int
  +fight(player) OutCome
  +meetText() void
  +getDescription() String
}

class Game {
  -double difficulty$
  -boolean gameOver$
  -int wavesDefeated$
  -String saveTo$
  -Input input$
  +gameplay()$ void
  +raiseDifficulty()$ void
  +waveWon()$ void
  +resetGame(player)$ Player
}

class Fight {
  -Input input$
  +wave(player, boss, waveCount)$ void
}

class EnemyFactory {
  +createRandomEnemy(boss)$ Enemy
}

class Upgrade {
  +Input input$
  +upgradeHall(player)$ boolean
  +checkLevel(amount, maxAmount)$ String
}

class Start {
  -Input input$
  +startScreen()$ void
  +initializePlayer()$ Player
  +validateName(name)$ void
  +tutorial()$ void
}

class End {
  +gameOverScreen(player)$ void
  +gameWinScreen(player)$ void
  +finalStats(player)$ void
}

class Saves {
  -Input input$
  +saveAndExit(player)$ void
  +loadGameScreen(saves)$ Player
}

class SaveData {
  -String name
  -int Id
  -int nextId$
  -int hp
  -int maxHp
  -int damage
  -double defense
  -double difficulty
  -int healthLevel
  -int damageLevel
  -int defenseLevel
  -int coins
  -int wavesDefeated
  -int enemyCount
  -String savedAt
  +SaveData(player)
  +SaveData(line)
  +toFileString() String
  +createPlayer() Player
  +printSummary(index) void
  +compareTo(other) int
  +updateNextId(path)$ void
}

class File {
  +load(path)$ String
  +readLines(path)$ List\~String\~
  +addLine(path, line)$ void
  +deleteLine(fileName, index)$ void
}

class Input {
  -Scanner input
  +getInput() String
  +getInput(prompt) String
  +getInput(prompt, options) String
}

class Main {
  +main(args)$ void
}

class NameValidationException {
  +NameValidationException(message)
}

class ComparableSaveData {
  <<interface>>
  +compareTo(other) int
}

class EnemyType {
  <<enumeration>>
  VAMPIRE
  DEMON
  SHAPESHIFTER
  LEVIATHAN
}

class OutCome {
  <<enumeration>>
  ENEMYWIN
  PLAYERWIN
  NONE
}

class Misc {
  +screenSpace()$ void
}

Entity <|-- Enemy : öröklés
Entity <|-- Player : öröklés
Enemy <|-- Demon : öröklés
Enemy <|-- Vampire : öröklés
Enemy <|-- Shapeshifter : öröklés
Enemy <|-- Leviathan : öröklés

CanHeal <|.. Player : megvalósítja
CanHeal <|.. Vampire : megvalósítja
CanHeal <|.. Leviathan : megvalósítja
ComparableSaveData <|.. SaveData : megvalósítja

Enemy ..> Player : fight(player)
Player ..> Entity : attack(target)
Enemy ..> EnemyType : type
Enemy ..> OutCome : fight() visszatérési érték
Fight ..> Player : használja
Fight ..> EnemyFactory : létrehoz
Fight ..> Enemy : harcol vele
Game ..> Fight : meghívja
Game ..> Start : meghívja
Game ..> Upgrade : meghívja
Game ..> End : meghívja
EnemyFactory ..> Enemy : létrehoz
EnemyFactory ..> Demon : létrehozhat
EnemyFactory ..> Vampire : létrehozhat
EnemyFactory ..> Shapeshifter : létrehozhat
EnemyFactory ..> Leviathan : főellenség
Saves ..> SaveData : mentéseket kezel
SaveData ..> Player : visszaállítja
Saves ..> File : fájlműveletek
SaveData ..> Game : játékállapotot állít vissza
SaveData ..> Enemy : ellenfélszámot állít vissza
Start ..> Player : létrehoz
Upgrade ..> Player : fejleszti
End ..> Player : statisztikákat mutat
Game ..> Input : használja
Fight ..> Input : használja
Start ..> Input : használja
Upgrade ..> Input : használja
Saves ..> Input : használja
Main ..> Game : elindítja

