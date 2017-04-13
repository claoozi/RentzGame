package claudia.rent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Date;

import models.GameLength;
import models.GameSingleton;
import models.Player;
import models.PlayersSize;

/**
 * Created by ei047234 on 4/11/17.
 */

public class SetupGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setupgame);

        String[] values = new String[] { "Player 1:", "Player 2:", "Player 3:",
                "Player 4:", "Player 5:", "Player 6:" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.setup_players_layout, R.id.textViewPlayer, values);

        ListView listView = (ListView) findViewById(R.id.listViewPlayers);
        listView.setAdapter(adapter);
    }

    public void nextButtonAction(View view) {

        ToggleButton buttonGameLength = (ToggleButton) findViewById(R.id.gameLengthButton);
        GameLength gameLength = GameLength.fromInt(buttonGameLength.isChecked() ?
                Integer.parseInt(buttonGameLength.getTextOn().toString()) :
                Integer.parseInt(buttonGameLength.getTextOff().toString()));

        ToggleButton buttonPlayersSize = (ToggleButton) findViewById(R.id.playersNrButton);
        PlayersSize playersSize = PlayersSize.fromInt(buttonPlayersSize.isChecked() ?
                Integer.parseInt(buttonPlayersSize.getTextOn().toString()) :
                Integer.parseInt(buttonPlayersSize.getTextOff().toString()));


        ListView listView = (ListView) findViewById(R.id.listViewPlayers);
        listView.
        //init the game
        GameSingleton gameSingleton = GameSingleton.getInstance();
        gameSingleton.setGameLength(gameLength);
        gameSingleton.setPlayersSize(playersSize);


        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player(""));


        gameSingleton.setPlayers(players);

        gameSingleton.startGame();
        Intent intent = new Intent(this, ChooseGameActivity.class);
        startActivity(intent);
    }
}
