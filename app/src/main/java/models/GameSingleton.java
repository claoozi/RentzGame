package models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.xml.datatype.Duration;

/**
 * Created by ei047234 on 4/12/17.
 */

public class GameSingleton {

    private static final GameSingleton ourInstance = new GameSingleton();

    public static GameSingleton getInstance() {
        return ourInstance;
    }

    private GameLength gameLength;
    private PlayersSize playersSize;
    private Date startedDate;
    private Date endedDate;
    private ArrayList<Player> players;
    private boolean gameHasStarted;

    private GameSingleton () { }

    public void startGame() {
        if(!gameHasStarted) {
            startedDate = new Date();
            gameHasStarted = true;
        }
    }

    public boolean checkGameFinished() {
        if(gameHasStarted) {
            //TODO: if all the games has been played than the game is finished

            if(endedDate == null) {
                endedDate = new Date();
            }

            return true;
        }

        return false;
    }

    public String durationString() {
        Date nowDate = new Date();
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(nowDate);
        calendar2.setTime(startedDate);
        long milsecs1= calendar1.getTimeInMillis();
        long milsecs2 = calendar2.getTimeInMillis();
        long s = milsecs2 - milsecs1;
        long days = s / (24 * 60 * 60);
        long rest = s - (days * 24 * 60 * 60);
        long hrs = rest / (60 * 60);
        long rest1 = rest - (hrs * 60 * 60);
        long min = rest1 / 60;
        long sec = s % 60;

        String dates = "";
        if (days > 0) dates = days + " Days ";

        dates += fill((int) hrs) + "h ";
        dates += fill((int) min) + "m ";
        dates += fill((int) sec) + "s ";

        return dates;
    }

    public String fill(int value)
    {
        String ret = String.valueOf(value);

        if (ret.length() < 2)
            ret = "0" + ret;
        return ret;
    }


    public GameLength getGameLength() {
        return gameLength;
    }

    public void setGameLength(GameLength gameLength) {
        this.gameLength = gameLength;
    }

    public PlayersSize getPlayersSize() {
        return playersSize;
    }

    public void setPlayersSize(PlayersSize playersSize) {
        this.playersSize = playersSize;
    }

    public Date getStartedDate() {
        return startedDate;
    }

    private void setStartedDate(Date startedDate) {
        this.startedDate = startedDate;
    }

    public Date getEndedDate() {
        return endedDate;
    }

    private void setEndedDate(Date endedDate) {
        this.endedDate = endedDate;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}
