package com.braincollaboration.mathboom.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.braincollaboration.mathboom.ClickListenerWrapper;
import com.braincollaboration.mathboom.R;

import org.seniorzhai.scoreboard.ScoreBoard;

import java.util.Random;

import me.itangqi.waveloadingview.WaveLoadingView;
import tyrantgit.explosionfield.ExplosionField;

public class gameActivity extends Activity {

    private ScoreBoard scoreView;
    private ExplosionField explosionField;
    private WaveLoadingView leftWave, rightWave;
    private TextView leftAnswerText, rightAnswerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        explosionField = ExplosionField.attach2Window(this);
        initViews();
    }

    private void initViews() {
        final Random rand = new Random();
        scoreView = (ScoreBoard) findViewById(R.id.score_view);
        leftWave = (WaveLoadingView) findViewById(R.id.waveLoadingView);
        leftAnswerText = (TextView)findViewById(R.id.tv_left_answer);
        leftAnswerText.setOnClickListener(new ClickListenerWrapper() {
            @Override
            public void onClickWrapped(View v) {
                explosionField.explode(v);
                leftAnswerText.setText(""+rand.nextInt(100));
            }
        });

        rightAnswerText = (TextView)findViewById(R.id.tv_right_answer);
        rightAnswerText.setOnClickListener(new ClickListenerWrapper() {
            @Override
            public void onClickWrapped(View v) {
                explosionField.explode(v);
                rightAnswerText.setText(""+rand.nextInt(100));
            }
        });

        Button btnGen = (Button) findViewById(R.id.btn_temp);
        btnGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreView.change(rand.nextInt(100));
                leftWave.setProgressValue(10);
                explosionField.explode(v);
            }
        });
    }

    public int getPercentsFromNumber(int leftTime, int levelTime) {
        return (leftTime * 100) / levelTime;
    }
}
