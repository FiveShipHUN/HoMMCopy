package me.eriknikli.homm.utils;

/**
 * Naplózó /Logger osztály, "függvénykönyvtár"
 */
public class Log {

    /**
     * Semmit nem logol
     */
    public static final int NONE = 0;

    /**
     * Csak alap információt logol
     */
    public static final int INFO = 1;

    /**
     * Csak alap információt és figyelmeztetést logol
     */
    public static final int WARN = 2;

    /**
     * Csak alap információt, figyelmeztetést és hibákat logol
     */
    public static final int ERR = 3;

    /**
     * Mindent logol
     */
    public static final int DEBUG = 4;

    /**
     * Aktuális log-level
     */
    private static int logLevel = INFO;

    /**
     * Beállítja a log-levelt
     *
     * @param i a kívánt log-level, lehet
     *          <ul>
     *              <li>
     *                  Log.NONE
     *              </li>
     *              <li>
     *                  Log.INFO
     *              </li>
     *              <li>
     *                  Log.WARN
     *              </li>
     *              <li>
     *                  Log.ERR
     *              </li>
     *              <li>
     *                  Log.DEBUG
     *              </li>
     *          </ul>
     */
    public static void setLogLevel(int i) {
        logLevel = i;
    }

    /**
     * @return a jelenlegi log-level
     */
    public static int logLvl() {
        return logLevel;
    }

    /**
     * alapvető információkat logol, ha a log-level megfelelő szintre van állítva
     *
     * @param s az üzenet, amit logolni kell
     */
    public static void info(String s) {
        if (logLvl() >= INFO) {
            System.out.println("[HoMM-Info] " + s);
        }
    }

    /**
     * figyelmeztetést logol, ha a log-level megfelelő szintre van állítva (shortcut {@code warn(s, null)}-ra)
     *
     * @param s az üzenet, amit logolni kell
     */
    public static void warn(String s) {
        warn(s, null);
    }

    /**
     * exceptiont logol, figyelmeztetésként, ha a log-level megfelelő szintre van állítva (shortcut {@code warn(e.getMessage(), e)}-re)
     *
     * @param e az exception, amit logolni kell
     */
    public static void warn(Exception e) {
        warn(e.getMessage(), e);
    }

    /**
     * figyelmeztetést logol exceptionnel, ha a log-level megfelelő szintre van állítva
     *
     * @param s az üzenet, amit logolni kell
     * @param e az exception, amit logolni kell
     */
    public static void warn(String s, Exception e) {
        if (logLvl() >= WARN) {
            System.out.println("[HoMM-Warning] " + s);
            if (e != null) {
                e.printStackTrace();
            }
        }
    }

    /**
     * hibaüzenetet logol, ha a log-level megfelelő szintre van állítva (shortcut {@code err(s, null)}-ra)
     *
     * @param s az üzenet, amit logolni kell
     */
    public static void err(String s) {
        err(s, null);
    }

    /**
     * exceptiont logol, hibaüzenetként, ha a log-level megfelelő szintre van állítva (shortcut {@code err(e.getMessage()}, e)-re)
     *
     * @param e az exception, amit logolni kell
     */
    public static void err(Exception e) {
        err(e.getMessage(), e);
    }

    /**
     * hibát logol exceptionnel, ha a log-level megfelelő szintre van állítva
     *
     * @param s az üzenet, amit logolni kell
     * @param e az exception, amit logolni kell
     */
    public static void err(String s, Exception e) {
        if (logLvl() >= ERR) {
            System.err.println("[HoMM-Error] " + s);
        }
    }

    /**
     * debug információt logol, ha a log-level megfelelő szintre van állítva (shortcut {@code debug(s, null)}-ra)
     *
     * @param s az üzenet, amit logolni kell
     */
    public static void debug(String s) {
        debug(s, null);
    }

    /**
     * exceptiont logol, debug üzenetként, ha a log-level megfelelő szintre van állítva (shortcut {@code debug(e.getMessage()}, e)-re)
     *
     * @param e az exception, amit logolni kell
     */
    public static void debug(Exception e) {
        debug(e.getMessage(), e);
    }

    /**
     * debug információt logol, exception-nel, ha a log-level megfelelő szintre van állítva
     *
     * @param s az üzenet, amit logolni kell
     * @param e az exception, amit logolni kell
     */
    public static void debug(String s, Exception e) {
        if (logLvl() >= DEBUG) {
            System.out.println("[HoMM-Debug] " + s);
            if (e != null) {
                e.printStackTrace();
            }
        }
    }

}
