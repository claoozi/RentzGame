package claudia.rent;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import models.EnterScoreAdapter;
import models.GameSingleton;
import models.Player;
import models.PlayerArrayAdapter;

/**
 * Created by ei047234 on 4/11/17.
 */

public class EnterScoreActivity extends AppCompatActivity {

    private EnterScoreAdapter enterScoreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterscore);

        //set time
        timerHandler.postDelayed(timerRunnable, 0);

        //set game name
        TextView textView = (TextView) findViewById(R.id.textViewGamePlayed);
        Player currentPlayer = GameSingleton.getInstance().getCurrentPlayer();
        textView.setText("S-a jucat " + currentPlayer.getCurrentGame().getGameType().getValue() + ".\n Introduce-ti punctele");

        //set EnterScoreAdapter
        if (enterScoreAdapter != null) {
            enterScoreAdapter.notifyDataSetChanged();
        }
        else{
            enterScoreAdapter = new EnterScoreAdapter(this,
                    R.layout.enter_score_layout, GameSingleton.getInstance().getPlayers());

            ListView listView = (ListView) findViewById(R.id.listViewPlayers);
            listView.setAdapter(enterScoreAdapter);
        }
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

        if(GameSingleton.getInstance().isGameFinished()){
            Intent intent = new Intent(this, RankingActivity.class);
            startActivity(intent);
        }
        else{

            //Navigate through all the players and setup the score final score;
            for(int i = 0;i<GameSingleton.getInstance().getPlayers().size(); i++){
                Player player = GameSingleton.getInstance().getPlayers().get(i);
                player.updateScoreWithTempScore();
            }

            // end current game;
            Player currentPlayer = GameSingleton.getInstance().getCurrentPlayer();
            currentPlayer.getCurrentGame().setCurrent(false);

            // set new player
            GameSingleton.getInstance().updateCurrentPlayerPosition();

            // navigate to choose game
            Intent intent = new Intent(this, ChooseGameActivity.class);
            startActivity(intent);
        }
    }
}
