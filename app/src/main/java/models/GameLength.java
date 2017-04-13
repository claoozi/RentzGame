package models;

/**
 * Created by ei047234 on 4/12/17.
 */

public enum GameLength {
    Short(4),
    Long(6);

    private final int value;

    GameLength(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }

    public static GameLength fromInt(int value) {
        for (GameLength type : GameLength.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }
}
