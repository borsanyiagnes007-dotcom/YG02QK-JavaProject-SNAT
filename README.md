# SNAT

## Projektösszefoglaló

A **SNAT** egy Java nyelven készülő, konzolos túlélőjáték. A játékos szörnyek egymást követő hullámaival küzd meg, miközben fejlesztheti karaktere életerejét, sebzését és védelmét. A legyőzött ellenfelek után érméket kap, amelyeket fejlesztésekre költhet. A cél a kilenc normál hullám túlélése, majd a végső főellenség, a Leviathan legyőzése.

A program új játék indítását, mentések betöltését és mentések kezelését is támogatja. A játékállapotot szöveges fájlban tárolja, így az elmentett játék később folytatható.

## Felhasználó és megoldandó probléma

* **Felhasználó:** az a játékos, aki egy egyszerű, konzolos szörnyharc-játékkal szeretne játszani.
* **Cél:** túlélni az egyre nehezebb ellenfeleket, karakterfejlesztésekkel növelni a túlélési esélyeket, és legyőzni a végső főellenséget.
* **Megoldás:** a program körökre osztott harcot, különböző ellenféltípusokat, gyógyulást, ideiglenes sebzésnövelést, karakterfejlesztéseket és fájlalapú mentést biztosít.

## Fő funkciók

* Új játék indítása és a játékos nevének megadása.
* Konzolos útmutató és menüvezérelt bemenet.
* Körökre osztott harc különböző szörnyekkel.
* Három normál ellenféltípus: Vampire, Shapeshifter és Demon.
* Leviathan mint végső főellenség.
* Ellenféltípusonként eltérő harci viselkedés.
* Életerő visszatöltése evéssel.
* Só használata a sebzés ideiglenes növelésére.
* Érmék szerzése győzelem után.
* Életerő-, sebzés- és védelemfejlesztés.
* A nehézség növekedése a hullámok előrehaladtával.
* Játékállapot mentése, mentések betöltése és mentések törlése.
* Mentések rendezése a teljesített hullámok száma szerint.
* Győzelmi és vereségi képernyő, valamint végső statisztikák.

## Technológia és előfeltételek

* **Nyelv:** Java.
* **Felület:** konzol.
* **Adattárolás:** szöveges fájlok.
* **Java-verzió:** **Java 26-ban lett fejlesztve.**
* **Buildeszköz:** Maven



## Használat

1. Indítsa el a programot a Main.java futtatásával.
2. Kövesse a kezdőképernyőn megjelenő útmutatót, és adja meg a játékos nevét.
3. Ha vannak korábbi mentések, választhat mentés betöltése vagy új játék indítása között.
4. Harc közben választhat a felkínált műveletek közül:

   * 'attack' – támadás;
   * 'eat' – gyógyulás, amelynél a játékos a következő ellenféltámadás helyett gyógyít;
   * 'salt' – a következő megfelelő támadáshoz sebzésnövelő hatást készít elő.
5. A győzelmek után érméket kap, és a fejlesztőteremben fejlesztheti a karakterét vagy elmentheti a jelenlegi játékállást. Ez utóbbi a program futásának végét is jelenti!
6. A mentési menüben mentést tölthet be, illetve mentést törölhet.
7. Túlélés esetén győzelmi képernyő jelenik meg; ha a játékos életereje elfogy, vereséget szenved.



## Objektumorientált tervezés

### Encapsulation – egységbezárás

Az osztályok állapotának jelentős része privát mezőkben van tárolva. A hozzáférést konstruktorok, getterek, setterek és műveletek biztosítják.

### Abstraction – absztrakció

* Az 'Entity' absztrakt osztály közös alapot biztosít a harcban részt vevő entitásoknak.
* Az 'Enemy' absztrakt osztály meghatározza az ellenfelek közös viselkedését, például a harcot és a találkozáskor megjelenő szöveget.
* A 'CanHeal' interfész a gyógyulás szerződését adja azoknak az osztályoknak, amelyek ezt a képességet megvalósítják.

### Inheritance and polymorphism – öröklődés és polimorfizmus

* A 'Player' az 'Entity' osztályból származik.
* Az 'Enemy' az 'Entity' osztályból származik.
* A 'Demon', 'Vampire', 'Shapeshifter' és 'Leviathan' az 'Enemy' leszármazottai.
* Az ellenfelek felülírják a harci viselkedést, így a játék a közös 'Enemy' típuson keresztül kezelheti őket, miközben mindegyik ellenfél eltérő szabályok szerint harcol.

### Kollekciók és rendezés

A 'Saves' osztály a mentések feldolgozásakor 'List<SaveData>' és 'ArrayList' kollekciókat használ. A 'SaveData' megvalósítja a 'Comparable<SaveData>' interfészt; a 'compareTo' a teljesített hullámok száma szerint, csökkenő sorrendben hasonlítja össze a mentéseket. A mentések listáját a 'Collections.sort' rendezi.

### Kivételek és bemenetellenőrzés

A projektben szerepel a saját 'NameValidationException' kivételosztály. A konzolos 'Input' osztály menüválasztás esetén az előre megadott lehetőségekre korlátozza a választást.

### Adatmegőrzés

A 'SaveData' a játékos és a játék állapotát tabulátorokkal elválasztott szöveges sorba alakítja. A 'File' osztály fájlbeolvasást, sorok olvasását, hozzáfűzést és sor törlését végzi; a 'Saves' osztály a mentések kezelőfelületét biztosítja.



## Választott tervezési minta: Simple Factory

A projekt a **Simple Factory** mintát használja az ellenségek létrehozására.

* **Részt vevő osztály:** 'EnemyFactory'.
* **Létrehozott típusok:** 'Vampire', 'Shapeshifter', 'Demon', illetve főellenségként 'Leviathan'.
* **Használat:** a 'Fight' osztály az 'EnemyFactory.createRandomEnemy(boss)' metódusán keresztül kér ellenfelet.
* A harci folyamattól elkülöníti az ellenfelek példányosítását és szabályait. Az ellenfél létrehozási logika egy helyen módosítható.



## Fő tervezési döntések

1. **Közös ősosztály az entitásoknak:** az 'Entity' összefogja a közös harci adatokat és műveleteket.
2. **Absztrakt ellenfélosztály:** az 'Enemy' egységes interfészt ad az eltérő harci mechanikájú ellenfelekhez.
3. **Képesség interfészen keresztül:** a 'CanHeal' csak azoknak az osztályoknak ad gyógyulási szerződést, amelyeknek szükségük van rá.
4. **Központi ellenséglétrehozás:** az 'EnemyFactory' elrejti a konkrét ellenfelek létrehozásának részleteit a harci logika elől.
5. **Külön mentési modell:** a 'SaveData' elválasztja a játékállapotot a szöveges fájlformátumtól és a mentések listázásától.
6. **Szöveges mentés:** az ember által is olvasható, soralapú formátum egyszerűvé teszi a mentést és visszatöltést.



## Ismert korlátok

* A mentési fájl sérülékenysége: A játék a mentett állásokat egy szöveges fájlban (saves.txt) tárolja. A mentések betöltése feltételezi, hogy a fájl a megfelelő formátumban tartalmazza az adatokat. Ha a fájlt a programon kívül módosítják, megsérül, vagy egyes adatok hiányoznak belőle, a mentés betöltése hibát okozhat, vagy váratlan működéshez vezethet.
* Konzolos felhasználói felület: A játék kizárólag konzolos felülettel rendelkezik, grafikus felhasználói felületet nem tartalmaz. A játékos szöveges menükön keresztül navigál, és a konzolba beírt válaszokkal választja ki a kívánt műveleteket.

