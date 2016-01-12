package com.braincollaboration.mathboom.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.braincollaboration.mathboom.R;

import org.seniorzhai.scoreboard.ScoreBoard;

import java.util.Random;

public class gameActivity extends Activity {

    private ScoreBoard scoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        initViews();
    }

    private void initViews() {
        scoreView = (ScoreBoard) findViewById(R.id.score_view);

        final Random rand = new Random();
        Button btnGen = (Button) findViewById(R.id.btn_temp);
        btnGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreView.change(rand.nextInt(100));
            }
        });
    }
}
