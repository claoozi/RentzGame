package models;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by ei047234 on 4/12/17.
 */


public class Player {
    private String name;
    private long score;
    private ArrayList<Game> games;

    public Player(String name) {
        this.name = name;

        games = new ArrayList<Game>();
        games.add(new Game(GameType.RedKing));
        games.add(new Game(GameType.Queens));
        games.add(new Game(GameType.Rombs));
        games.add(new Game(GameType.WistPlus));
        games.add(new Game(GameType.WistMinus));
        games.add(new Game(GameType.TotalPlus));
        games.add(new Game(GameType.TotalMinus));
        games.add(new Game(GameType.Rent));
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public ArrayList<Game> getGames(){
        return games;
    }


    public interface Predicate<T> { boolean apply(T type); }

    public static <T> Collection<T> filter(Collection<T> col, Predicate<T> predicate) {
        Collection<T> result = new ArrayList<T>();
        for (T element: col) {
            if (predicate.apply(element)) {
                result.add(element);
            }
        }
        return result;
    }

    public ArrayList<Game> gamesNotPlayed(){

        Predicate<Game> gamesNotPlayedPredicate = new Predicate<Game>() {
            public boolean apply(Game subGame) {
                return subGame.isPlayed() == false ;
            }
        };

        Collection<Game> result = filter(games, gamesNotPlayedPredicate);

        return (ArrayList<Game>) result;
    }

    public boolean areGamesUnplayed(){
        return !gamesNotPlayed().isEmpty();
    }
}

