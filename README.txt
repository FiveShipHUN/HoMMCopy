A projekt IntellijIDEA-ben készült, a fordítás és futtatás ott volt
tesztelve, kérlek, hogy ott próbáld meg futtatni.
A JDK-n és a JUnit-on kívül semmilyen külső könyvtárat nem használtam.
Készítettem a "build" mappába egy jar filet, ezzel is futtatható a játék. Lehet, hogy
elfelejtem későbbi változatokba frissíteni, szóval légyszíves csak akkor használd, ha
valami miatt nem tudod futtatni a játékot IntellijIDEA-n keresztül.
A projektet csomagold ki egy mappába, lehetőleg egy HoMMCopy nevűbe. Ezt a mappát
nyisd meg IntellijIDEA-ben. Ezután keresd meg a src mappában a me.eriknikli.homm.HoMM
osztályt, amiben megtalálod a main függvényt, ezt kell elindítanod.
(Vagy a class vagy a main függvény deklarálása melletti nyilat nyomd meg.)

Ha esetleg terminálból szeretnéd fordítani a programot arra figyelj, hogy az asseteket
a res mappában találod (a forráskódot pedig az src-ben), ezeket is bele kell fordítanod a 
JAR file-ba, különben nem fog rendeltetésszerűen működni a program.

A játékot 1280x768-as ablakméretben terveztem és teszteltem,
kérlek te is próbáld meg ebben futtatni.
Azonban a játék generál egy config file-t, ezt szerkesztheted,
ha szeretnéd. Emellett vannak parancssori argumentumok is:
    -ignoreconfig, ezzel a config file beállításait nem veszi figyelembe,
        fontos, mert enélkül sok parancssori beállításnak nem lesz hatása

    -x <szám>, az ablak x helyét állítja be

    -y <szám>, az ablak y helyét állítja be

    -width <szám>
    -w <szám>, az ablak hosszát állítja be
    
    -height <szám>
    -h <szám>, az ablak magasságát állítja be

    -maximize
    -mw
    -maximizewindow, az ablakot kinagyított állapotba nyissa meg

    -loglevel <NONE|INFO|WARN|ERR|DEBUG>, a loglevelt állítja

    -resizable, az ablak mérete futás alatt változtatható

    -notresizable, az ablak mérete futás alatt NEM változtatható (ez az alap)

Ha két egymással nem összeegyeztethető beállítás érkezik, akkor az utóbbit fogja figyelembe venni,
a config fájl beállításait pedig (hacsak nem megkértük rá a programot, hogy ne), mindig az argumentumok
feldolgozása után állítja be.

A játékot a "Start Game" gombbal kezdheted. Ezután meg fogja kérdezni, milyen nehézségi szinten szeretnél játszani.
Ezután össze lehet rakni a saját hadsereged, illetve meg tudod nézni az ellenfeledet, hogy ő mivel megy csatába.
A legtöbb vásárlást vissza tudod vonni, illetve van Reset gomb is, ami mindent visszavon. Van random hős is,
de ez a funkció a No Limit nehézségi szinten ki van kapcsolva.

A tulajdonságpontok (Skillek) leírását úgy érheted el, hogy ráviszed a nevére a kurzort, és ott tartod.
Az egységek és varázslatok leírását a ? ikonnal érheted el.

Miután készen állsz, a Start Battle gombbal tudsz csatába menni.

Egy új ablak megjelenik a csata megkezdésekor. Ez az eseményeket listázza, kicsit nehéz
követni mit csinál a gép, ezért raktam bele, bár nem hiszem, hogy sokat segítettem vele, sry.

A csata előtt a játék random lehelyezi az egységeket, de mielőtt a tényleges csata megkezdődik szabadon pakolhatod az
első két oszlopban ezeket. Ezután a bal alsó sarokba lévő Fight! gombbal tudod megkezdeni a csatát.

A saját és ellenfeled hősének adatait meg tudod nézni, ha ráviszed az egeret az adott hős nevére.

Bal oldalt láthatod, hogy éppen hanyadik körben jár a csata, és az egyégek sorrendjét. A nagy szám az egyégek mellett
a kezdeményezést jelzi (beleszámítva a hős morálját).

Varázsolni a hős egységei alatti gombokkal tudsz. Először ki kell választani, melyik varázslatot szeretnéd alkalmazni
(a támadást is varázslatként kezeltem, de ezenkívül minden leírtnak megfelel, ha minden igaz). Ha kell, akkor ezután
ki kell választani a célpontot, de van olyan varázslat, ami nem igényel ilyet. Vigyázz! Tudsz baráti egységeket is
sebezni, illetve a semmit is (így tudsz tűzlabdát használni két egység között). Varázslatot visszavonni úgy tudsz, hogy
ismét rákattintasz a varázslat gombjára.

Extra varázslatok:
    Erősítés: megnöveli a támadását a következő egységnek.
    Betegség: ellenséges egység egy körből kimarad, csak akkor van hatása, ha az ellenfélnek legalább két egysége van.

Az egységeket tudod mozgatni. Sárgával jelöli ki a soron következő egységet. Szürkével azokat a mezőket ahova mozoghat.
Naranccsal a támadható egységeket. Zölddel az egyéb baráti, pirossal az ellenséges egységeket. Ha várakozni szeretnél
az egységgel, akkor csak kattints rá, és így kimarad abból a körből. Támadáshoz kattints egy narancssárga mezőre,
mozgáshoz egy szürke mezőre.

A BOT teljesen random mit csinál, viszont mindig támad, ha van rá lehetősége (ezért az íjászok pl. soha nem mozognak).

Ha valakinek elfogynak az egységei, akkor véget ér a játék. Ilyenkor egy Message Box megjelenik ami értesít erről, majd
kidob a főmenübe. Előfordulhat, hogy nem jelenik meg ez az üzenet, ez általába varázslat után van, ilyenkor be kell
fejezni az adott kört. (Elvileg javítottam.)

Extra egységek:
    Kardforgató: területi sebzést okoz, baráti egységet nem sebez
    Pap: magán kívül a közelbe lévő egységeket köre elején gyógyítja

Két osztály JavaDoc-kal:
    me.eriknikli.homm.HoMM
    me.eriknikli.homm.utils.RNG
    van még egy rakat, de csak kettő kellett (kár hogy hamarabb erről nem tudtam :c)
Tesztek:
    me.eriknikli.homm.test.
        AddUnitsTest: leteszteli, hogy az adott unitokat hozzáadja és mergeli-e (összevonja-e) ha kell
        ClampTest: me.eriknikli.homm.utils.Utils.clamp() függvény működését ellenőrzi
            szélsőérték: ha a max és min egyezik, vagy meg van cserélve
        DifficultyDisplayNameTest: leellenőrzi, hogy a me.eriknikli.homm.gameplay.Difficulty enum jól működik-e, és user
            friendly nevet ír-e ki
        HeroGoldTest: leellenőrzi, hogy adott hős megfelelő aranyat kap-e, illetve a költekezést is nézi
        RandomTest: a me.eriknikli.homm.utils.RNG osztály tesztelése
            szélsőérték: olyan intervallum, ahol az alsó és felsőkorlát meg van cserélve
            100-szor fut le a tesztelés, hisz random, nem lehet pontosan belőni teszteléskor mi fog történni
        RangeTest: a me.eriknikli.homm.utils.Range osztály tesztelése
            Szélsőérték: olyan range, ahol az alsó és felsőkorlát meg van cserélve
        SpellPropertiesTest: a leírtaknak megfelelőek-e a varázslatok
        UnitPropertiesTest: a leírtaknak megfelelőek-e az egység tesztek
        SkillBuyingTest: leteszteli, hogy a Skillek ára megfelelően inflálódik
        RandomAIHeroTest: leteszteli, hogy képes-e a gép időben hőst generálni, és annak lesznek-e egységei







































































(PS: tudom, hogy nem a legjobb az angolom, de se hunglishul, se full magyarítva nem vagyok hajlandó játékot készíteni c:)