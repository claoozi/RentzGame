package models;

/**
 * Created by ei047234 on 4/12/17.
 */

public enum GameType {
    RedKing("Popa de inima rosie"),
    Queens("Dame"),
    Rombs("Romburi"),
    TotalPlus("Totale cu plus"),
    TotalMinus("Totale cu minus"),
    WistPlus("Wist cu plus"),
    WistMinus("Wist cu minus"),
    Rent("Rentz");

    private final String value;

    GameType(final String newValue) {
        value = newValue;
    }

    public String getValue() { return value; }
}
