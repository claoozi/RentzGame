package claudia.rent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by ei047234 on 4/11/17.
 */

public class EnterScoreActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterscore);
    }

    public void nextButtonAction(View view) {

        //if(game is finished){

        //    Intent intent = new Intent(this, RankingActivity.class);
        //    startActivity(intent);

        //}
        //else{
            Intent intent = new Intent(this, ChooseGameActivity.class);
            startActivity(intent);
        //}
    }
}
