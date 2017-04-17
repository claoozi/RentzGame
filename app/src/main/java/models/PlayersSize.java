package models;

/**
 * Created by ei047234 on 4/12/17.
 */

public enum PlayersSize {
    Small(4),
    Medium(5),
    Big(6);

    private final int value;

    PlayersSize(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }

    public static PlayersSize fromInt(int value) {
        for (PlayersSize type : PlayersSize.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }
}
