package claudia.rent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

        this.setGameLength();
        this.setPlayersSize();

//        GameSingleton gameSingleton = GameSingleton.getInstance();
//
//        ListView listView = (ListView) findViewById(R.id.listViewPlayers);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                GameSingleton gameSingleton = GameSingleton.getInstance();
//                gameSingleton
//                Book data = (Book) parent.getItemAtPosition(position);
//                datasource.editBook(data,data.getName() + " 2014", data.getWriter() + "," + data.getWriter(), data.getYear());
//                adapter.notifyDataSetChanged();
//            }
//        });
//
//
//
//
//
//        String[] values2 = new String[] {};
//        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
//                R.layout.setup_players_layout, R.id.editText, values2);
//        listView.setAdapter(adapter2);
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

    public void onClickGameLength(View view) {
        this.setGameLength();
    }

    public void onClickPlayersSize(View view){
        this.setPlayersSize();
    }

    private  void setGameLength(){
        ToggleButton buttonGameLength = (ToggleButton) findViewById(R.id.gameLengthButton);
        GameLength gameLength = GameLength.fromInt(buttonGameLength.isChecked() ?
                Integer.parseInt(buttonGameLength.getTextOn().toString()) :
                Integer.parseInt(buttonGameLength.getTextOff().toString()));

        GameSingleton gameSingleton = GameSingleton.getInstance();
        gameSingleton.setGameLength(gameLength);
    }

    private  void setPlayersSize(){
        ToggleButton buttonPlayersSize = (ToggleButton) findViewById(R.id.playersNrButton);
        PlayersSize playersSize = PlayersSize.fromInt(buttonPlayersSize.isChecked() ?
                Integer.parseInt(buttonPlayersSize.getTextOn().toString()) :
                Integer.parseInt(buttonPlayersSize.getTextOff().toString()));

        GameSingleton gameSingleton = GameSingleton.getInstance();
        gameSingleton.setPlayersSize(playersSize);

        String[] playerLabels = new String[] {};
        for (int i = 0; i< playersSize.getValue(); i++){
            playerLabels[i] = "Player " + String.valueOf(i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.setup_players_layout, R.id.textViewPlayer, playerLabels);

        ListView listView = (ListView) findViewById(R.id.listViewPlayers);
        listView.setAdapter(adapter);
    }
}
