package claudia.rent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by ei047234 on 4/11/17.
 */

public class RankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
    }

    public void saveGame(View view) {
        //TODO: save the game
        // navigate to home

        Intent intent = new Intent(this,  HomeActivity.class);
        startActivity(intent);
    }
}
