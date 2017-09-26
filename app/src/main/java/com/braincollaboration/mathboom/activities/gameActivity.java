package com.braincollaboration.mathboom.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.braincollaboration.mathboom.ClickListenerWrapper;
import com.braincollaboration.mathboom.R;
import com.braincollaboration.mathboom.functiongenerator.Function;
import com.braincollaboration.mathboom.functiongenerator.MathExpressionGenerator;

import org.seniorzhai.scoreboard.ScoreBoard;

import me.itangqi.waveloadingview.WaveLoadingView;
import tyrantgit.explosionfield.ExplosionField;

public class gameActivity extends Activity {

    private final static long MAX_LEVEL_TIME = 10000;
    private final static int COUNT_DOWN_INTERVAL = 1000;  //Interval to update timers. MilliSeconds
    private static final int LEFT_SIDE_ID = 100;
    private static final int RIGHT_SIDE_ID = 200;

    private int score = 0;

    private ScoreBoard scoreView;
    private ExplosionField explosionField;
    private WaveLoadingView leftWave, rightWave;
    private TextView leftAnswerText, rightAnswerText;
    private TextView leftQuestionText, rightQuestionText;
    private CountDownTimer countDownTimerLeft, countDownTimerRight;
    private ImageView leftExplosionField, rightExplosionField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        explosionField = ExplosionField.attach2Window(this);
        initViews();
        startSideTimer(LEFT_SIDE_ID, MAX_LEVEL_TIME);
    }

    private void initViews() {
        leftExplosionField = (ImageView) findViewById(R.id.left_explosion_field);
        rightExplosionField = (ImageView) findViewById(R.id.right_explosion_field);
        scoreView = (ScoreBoard) findViewById(R.id.score_view);
        leftWave = (WaveLoadingView) findViewById(R.id.wave_left);
        rightWave = (WaveLoadingView) findViewById(R.id.wave_right);
        leftQuestionText = (TextView) findViewById(R.id.tv_left_question);
        rightQuestionText = (TextView) findViewById(R.id.tv_right_question);

        leftAnswerText = (TextView) findViewById(R.id.tv_left_answer);
        leftAnswerText.setOnClickListener(new ClickListenerWrapper(leftExplosionField) {
            @Override
            public void onClickWrapped(View v) {
                Function randFunction = MathExpressionGenerator.getInstance().getRandomExpression();
                leftAnswerText.setText(randFunction.getResult());
                leftQuestionText.setText(randFunction.getFunction());
                startSideTimer(LEFT_SIDE_ID, MAX_LEVEL_TIME);
                scoreView.change(score++);
                explosionField.explode(findViewById(R.id.left_explosion_field));
            }
        });

        rightAnswerText = (TextView) findViewById(R.id.tv_right_answer);
        rightAnswerText.setOnClickListener(new ClickListenerWrapper(rightExplosionField) {
            @Override
            public void onClickWrapped(View v) {
                Function randFunction = MathExpressionGenerator.getInstance().getRandomExpression();
                rightAnswerText.setText(randFunction.getResult());
                rightQuestionText.setText(randFunction.getFunction());
                startSideTimer(RIGHT_SIDE_ID, MAX_LEVEL_TIME);
                scoreView.change(score++);
                explosionField.explode(findViewById(R.id.right_explosion_field));
            }
        });
    }

    private void startSideTimer(int sideID, final long levelTime) {
        switch (sideID) {
            case LEFT_SIDE_ID:
                if (countDownTimerLeft != null) {
                    countDownTimerLeft.cancel();
                }
                countDownTimerLeft = new CountDownTimer(levelTime, COUNT_DOWN_INTERVAL) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        leftWave.setProgressValue((int) getPercentsFromNumber(millisUntilFinished, MAX_LEVEL_TIME));
                    }

                    @Override
                    public void onFinish() {
//                        endLevel();
                    }
                };
                countDownTimerLeft.start();
                break;
            case RIGHT_SIDE_ID:
                if (countDownTimerRight != null) {
                    countDownTimerRight.cancel();
                }
                countDownTimerRight = new CountDownTimer(levelTime, COUNT_DOWN_INTERVAL) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        rightWave.setProgressValue((int) getPercentsFromNumber(millisUntilFinished, MAX_LEVEL_TIME));
                    }

                    @Override
                    public void onFinish() {
//                        endLevel();
                    }
                };
                countDownTimerRight.start();
                break;
        }
    }

    public long getPercentsFromNumber(long leftTime, long levelTime) {
        return (leftTime * 100) / levelTime;
    }
}
