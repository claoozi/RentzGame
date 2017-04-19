package claudia.rent;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import models.GameArrayAdapter;
import models.GameSingleton;
import models.Player;

/**
 * Created by ei047234 on 4/11/17.
 */

public class ChooseGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosegame);

        timerHandler.postDelayed(timerRunnable, 0);

        //set player name
        TextView textView = (TextView) findViewById(R.id.textViewPlayer);
        textView.setText(GameSingleton.getInstance().getCurrentPlayer().getName() + " alege urmatorul joc.");

        // create adapter
        Player currentPlayer = GameSingleton.getInstance().getCurrentPlayer();
        GameArrayAdapter adapter = new GameArrayAdapter(this,
                R.layout.choose_game_layout, currentPlayer.getGames());

        //set adapter
        ListView listView = (ListView) findViewById(R.id.listViewGames);
        listView.setAdapter(adapter);
    }

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            TextView textView = (TextView) findViewById(R.id.textViewTime);
            textView.setText(GameSingleton.getInstance().durationString());

            timerHandler.postDelayed(this, 1000);
        }
    };

    public void nextButtonAction(View view) {
        Intent intent = new Intent(this, EnterScoreActivity.class);
        startActivity(intent);
    }
}
