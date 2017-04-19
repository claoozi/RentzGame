package models;

/**
 * Created by ei047234 on 4/12/17.
 */

public class Game {

    private GameType gameType;
    private boolean played;
    private boolean isCurrent;

    public Game(GameType gameType) {
        this.gameType = gameType;
        played = false;
    }

    public GameType getGameType() {
        return gameType;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }
}
