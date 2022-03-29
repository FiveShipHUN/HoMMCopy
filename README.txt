A projekt IntellijIDEA-ben készült, a fordítás ott volt tesztelve,
kérlek, hogy ott próbáld meg futtatni.
A JDK-n és a JUnit-on kívül semmilyen külső könyvtárat nem használtam.
Elvileg, ha nem felejtem el (ezek szerint elfelejtettem, mert ez a szöveg itt maradt c:),
fogok készíteni egy build mappát, ebben megtalálod a lefordult programot futtatható állapotban.
A main függvény a me.eriknikli.homm.HoMM class-be találod.

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















































































(PS: tudom, hogy nem a legjobb az angolom, de se hunglishul, se full magyarítva nem vagyok hajlandó játékot készíteni c:)