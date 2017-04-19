package models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.xml.datatype.Duration;

import static android.R.attr.duration;

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
    private int currentPlayerPosition;

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
        long duration  = (new Date()).getTime() - startedDate.getTime();

        long diffInHours = TimeUnit.MILLISECONDS.toHours(duration) % 24;
        long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
        diffInMinutes = diffInMinutes % (60 * (diffInHours > 0 ? diffInHours : 1));
        long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
        diffInSeconds = diffInSeconds % (60 * (diffInMinutes > 0 ? diffInMinutes : 1));

        String dates = "";
        dates += fill((int) diffInHours) + ":";
        dates += fill((int) diffInMinutes) + ":";
        dates += fill((int) diffInSeconds);

        //test`
        return dates;
    }

    public void editPlayer(int position, String name){
        ((Player)this.players.get(position)).setName(name);
    }

    public String fill(int value)
    {
        String ret = String.valueOf(value);

        if (ret.length() < 2)
            ret = "0" + ret;
        return ret;
    }

    public Player getCurrentPlayer(){
        return players.get(currentPlayerPosition);
    }

    public void updateCurrentPlayerPosition(){
        //set the next player the current player
        currentPlayerPosition = currentPlayerPosition == players.size() - 1 ? 0 : currentPlayerPosition + 1;
    }

    public boolean validPlayersNames(){
        for(int i = 0; i<players.size(); i++){
            Player player = players.get(i);
            if(player.getName().isEmpty()){
                return false;
            }
        }

        return true;
    }

    public GameLength getGameLength() {
        return gameLength;
    }

    public void setGameLength(GameLength gameLength) {
        this.gameLength = gameLength;

        if(players != null) {
            for (int i = 0; i < players.size(); i++) {
                players.get(i).updateWithGameLength(gameLength);
            }
        }
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

    public boolean isGameFinished() {
        boolean isGameFinished = true;

        for(int i = 0; i<players.size(); i++){
            boolean areUnplayedGames = players.get(i).areUnplayedGames();
            if(areUnplayedGames){
                isGameFinished = false;
                break;
            }
        }

        if(isGameFinished && endedDate == null){
            endedDate = new Date();
        }

        return isGameFinished;
    }
}
