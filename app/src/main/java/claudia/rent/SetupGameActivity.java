package claudia.rent;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Date;

import models.GameLength;
import models.GameSingleton;
import models.Player;
import models.PlayerArrayAdapter;
import models.PlayersSize;

/**
 * Created by ei047234 on 4/11/17.
 */

public class SetupGameActivity extends AppCompatActivity {

    private PlayerArrayAdapter playerArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setupgame);

        this.setGameLength();
        this.setPlayersSize();

        RadioGroup gameLengthRadioGroup = (RadioGroup) findViewById(R.id.gameRadioGroup);
        gameLengthRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setGameLength();
            }
        });
        RadioGroup playersSizeRadioGroup = (RadioGroup) findViewById(R.id.playersRadioGroup);
        playersSizeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setPlayersSize();
            }
        });
    }

    public void nextButtonAction(View view) {

        if(GameSingleton.getInstance().validPlayersNames()) {
            ListView listView = (ListView) findViewById(R.id.listViewPlayers);
            GameSingleton.getInstance().startGame();
            Intent intent = new Intent(this, ChooseGameActivity.class);
            startActivity(intent);
        }
        else{
            // show message to add all players names
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            dlgAlert.setMessage("Completati numele tuturor jucatorilor.");
            dlgAlert.setTitle("Rentz");
            dlgAlert.setCancelable(true);
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            dlgAlert.create().show();
        }
    }

    private  void setGameLength(){
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.gameRadioGroup);
        GameLength gameLength = radioGroup.getCheckedRadioButtonId() == R.id.longRadioButton ? GameLength.Long :GameLength.Short;

        GameSingleton gameSingleton = GameSingleton.getInstance();
        gameSingleton.setGameLength(gameLength);
    }

    private  void setPlayersSize(){

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.playersRadioGroup);
        RadioButton checkedRadioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
        PlayersSize playersSize = PlayersSize.fromInt(Integer.parseInt(checkedRadioButton.getText().toString()));

        GameSingleton gameSingleton = GameSingleton.getInstance();
        gameSingleton.setPlayersSize(playersSize);

        ArrayList<Player> players = gameSingleton.getPlayers();
        if(players == null){
            players = new ArrayList<>();
        }

        if(players.size() != playersSize.getValue()) {
            if (players.size() < playersSize.getValue()) {
                for (int i = players.size(); i < playersSize.getValue(); i++) {
                    players.add(new Player(i));
                }
            } else {
                for (int i = playersSize.getValue(); i < players.size(); i++) {
                    players.remove(i);
                    i--;
                }
            }

            GameSingleton.getInstance().setPlayers(players);

            if (playerArrayAdapter != null) {
                playerArrayAdapter.notifyDataSetChanged();
            }
            else{
                playerArrayAdapter = new PlayerArrayAdapter(this,
                        R.layout.setup_players_layout, gameSingleton.getPlayers());

                ListView listView = (ListView) findViewById(R.id.listViewPlayers);
                listView.setAdapter(playerArrayAdapter);
            }
        }
    }
}
