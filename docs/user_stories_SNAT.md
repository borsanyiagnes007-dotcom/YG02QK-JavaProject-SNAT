# User stories – SNAT

1. **Karakter létrehozása**

   * Mint játékos, szeretnék karakternevet megadni és új játékost létrehozni, hogy elkezdhessem a játékot.
   * Elfogadási feltétel: a játékos inicializálása után rendelkezésre áll egy Player objektum alapértékekkel.
2. **Szörny elleni harc**

   * Mint játékos, szeretnék minden hullámban egy ellenféllel megküzdeni, hogy továbbhaladhassak a játékban.
   * Elfogadási feltétel: a Fight egy ellenfelet kér az EnemyFactory-től; a játékos támadhat, ehet vagy sót használhat; a harc győzelemig vagy vereségig tart.
3. **Változatos ellenfelek**

   * Mint játékos, szeretnék különböző képességű szörnyekkel találkozni, hogy a harcok változatosak legyenek.
   * Elfogadási feltétel: a gyár normál ellenfélként Vampire, Shapeshifter vagy Demon példányt adhat, főellenségként pedig Leviathant.
4. **Karakter gyógyítása**

   * Mint játékos, szeretném gyógyítani a karakteremet, hogy növeljem a túlélési esélyemet.
   * Elfogadási feltétel: a Player heal metódusa növeli az életerőt, de nem engedi a maxHp fölé.
5. **Fejlesztések vásárlása**

   * Mint játékos, szeretném érmékért fejleszteni az életerőmet, sebzésemet vagy védelmemet, hogy erősebbé váljak.
   * Elfogadási feltétel: a fejlesztés csak elegendő érme esetén történik meg.
6. **Játék mentése**

   * Mint játékos, szeretném elmenteni a karakterem és a játékmenet állapotát, hogy később folytathassam.
   * Elfogadási feltétel: a SaveData eltárolja a játékos fő statisztikáit, a nehézséget, a teljesített hullámokat és az ellenfelek számlálóját; a Saves fájlba írja az adatot.
7. **Mentés betöltése és törlése**

   * Mint játékos, szeretnék korábbi mentést betölteni vagy törölni, hogy kezelhessem a mentett játékaimat.
   * Elfogadási feltétel: a mentések listázhatók, hullámszám szerint rendezhetők, betölthetők és törölhetők.
8. **Játék vége és eredmények**

   * Mint játékos, szeretném látni a győzelmi vagy vereségi képernyőt és a végső statisztikáimat, hogy áttekintsem az eredményemet.
   * Elfogadási feltétel: az End megjeleníti a befejezés típusát és a karakter fő statisztikáit.

