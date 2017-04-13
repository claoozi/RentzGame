package claudia.rent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by ei047234 on 4/11/17.
 */

public class ChooseGameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosegame);
    }

    public void nextButtonAction(View view) {
        Intent intent = new Intent(this, EnterScoreActivity.class);
        startActivity(intent);
    }
}
