package sideproject.jeanie;

import android.animation.ValueAnimator;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

// import java.util.Random;


public class MapOne extends AppCompatActivity implements View.OnTouchListener {

    int score = 0;
    int scoreIncreaseRate = 100;
    int boundary = 100;
    int minBoundary = 1;
//    Random initialRandom = new Random();
//    int randomSpeed = initialRandom.nextInt(3000) + 2000;

    Button buttonLeft, buttonMidOne, buttonMidTwo, buttonRight;
    TextView txtScore, finale;
    ValueAnimator valueAnimatorDropLeft = ValueAnimator.ofFloat(-1000f, 2000f);
    ValueAnimator valueAnimatorDropMidOne = ValueAnimator.ofFloat(-1000f, 2000f);
    ValueAnimator valueAnimatorDropMidTwo = ValueAnimator.ofFloat(-1000f, 2000f);
    ValueAnimator valueAnimatorDropRight = ValueAnimator.ofFloat(-1000f, 2000f);

    int[] delayButtonLeft = {1500, 1500, 1500, 1500, 1500, 1500, 1500, 1500, 1500, 1500, 1500, 3000, 3000, 3000, 3000};
    int[] delayButtonMidOne = {2000, 6000, 9000, 12000, 2000, 6000, 9000};
    int[] delayButtonMidTwo = {2000, 6000, 9000, 12000, 2000, 6000, 9000};
    int[] delayButtonRight = {30000, 30000, 30000, 30000, 30000, 30000, 30000, 30000};
    int counterLeft = 0;
    int counterMidOne = 0;
    int counterMidTwo = 0;
    int counterRight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapone);

        buttonLeft = findViewById(R.id.buttonLeft);
        buttonMidOne = findViewById(R.id.buttonMidOne);
        buttonMidTwo = findViewById(R.id.buttonMidTwo);
        buttonRight = findViewById(R.id.buttonRight);
        finale = findViewById(R.id.txtFinale);
        txtScore = findViewById(R.id.txtScore);

        final MediaPlayer mediaPlayer = MediaPlayer.create(MapOne.this, R.raw.mapone);
        mediaPlayer.start();

        valueAnimatorDropLeft.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimatorDropLeft.setDuration(1500);
        valueAnimatorDropLeft.setRepeatCount(ValueAnimator.INFINITE);

        valueAnimatorDropMidOne.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimatorDropMidOne.setDuration(2000);
        valueAnimatorDropMidOne.setRepeatCount(ValueAnimator.INFINITE);

        valueAnimatorDropMidTwo.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimatorDropMidTwo.setDuration(2000);
        valueAnimatorDropMidTwo.setRepeatCount(ValueAnimator.INFINITE);

        valueAnimatorDropRight.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimatorDropRight.setDuration(3000);
        valueAnimatorDropRight.setRepeatCount(ValueAnimator.INFINITE);

        valueAnimatorDropLeft.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                final float progress = (float) animation.getAnimatedValue();
                buttonLeft.setTranslationY(progress);
            }

        });

        valueAnimatorDropLeft.start();

        valueAnimatorDropMidOne.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                final float progress = (float) animation.getAnimatedValue();
                buttonMidOne.setTranslationY(progress);
            }

        });

        valueAnimatorDropMidOne.start();

        valueAnimatorDropMidTwo.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                final float progress = (float) animation.getAnimatedValue();
                buttonMidTwo.setTranslationY(progress);
            }

        });

        valueAnimatorDropMidTwo.start();

        valueAnimatorDropRight.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                final float progress = (float) animation.getAnimatedValue();
                buttonRight.setTranslationY(progress);
            }

        });

        valueAnimatorDropRight.start();

        buttonLeft.setOnTouchListener(this);
        buttonMidOne.setOnTouchListener(this);
        buttonMidTwo.setOnTouchListener(this);
        buttonRight.setOnTouchListener(this);


    /*    class CheckCondition extends TimerTask {
            public void run() {
                if (valueAnimatorDropLeft.isStarted() != true)
                    ++counterLeft;
                    valueAnimatorDropLeft.cancel();
                    valueAnimatorDropLeft.setDuration(delayButtonLeft[counterLeft]);
                    valueAnimatorDropLeft.start();
            }
        }

        Timer timer = new Timer();
        timer.schedule(new CheckCondition(), 0 , 1); */

        Context context = getApplicationContext();
        CharSequence missedText = "Missed!";
        int duration = Toast.LENGTH_SHORT;

        final Toast missedMessage = Toast.makeText(context, missedText, duration);
        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(buttonLeft.getY() <= 2000 - minBoundary) {
                    handler.postDelayed(this, 1);
                }
                else {
                    ++counterLeft;
                    valueAnimatorDropLeft.cancel();
                    valueAnimatorDropLeft.setDuration(delayButtonLeft[counterLeft]);
                    valueAnimatorDropLeft.start();
                    missedMessage.show();
                    handler.postDelayed(this, 1);
                }
            }
        }, 1);
}

    @Override
    public boolean onTouch (View v, MotionEvent event) {

        Context context = getApplicationContext();
        CharSequence congratsText = "Perfect!";
        int duration = Toast.LENGTH_SHORT;

        Toast congratulatoryMessage = Toast.makeText(context, congratsText, duration);

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            if (v.getId() == buttonLeft.getId()) {

                if (finale.getY() + boundary >= buttonLeft.getY() && buttonLeft.getY() >= finale.getY() - boundary) {
                    score += scoreIncreaseRate;
                    txtScore.setText(String.format("Score: %d", score));
                    congratulatoryMessage.show();
                    ++counterLeft;
                    valueAnimatorDropLeft.cancel();
                    valueAnimatorDropLeft.setDuration(delayButtonLeft[counterLeft]);
                    valueAnimatorDropLeft.start();

                } else {
                    ++counterLeft;
                    valueAnimatorDropLeft.cancel();
                    valueAnimatorDropLeft.setDuration(delayButtonLeft[counterLeft]);
                    valueAnimatorDropLeft.start();
                }

            } else if (v.getId() == buttonMidOne.getId()) {

                if (finale.getY() + boundary >= buttonMidOne.getY() && buttonMidOne.getY() >= finale.getY() - boundary) {
                    score += scoreIncreaseRate;
                    txtScore.setText(String.format("Score: %d", score));
                    congratulatoryMessage.show();
                    ++counterMidOne;
                    valueAnimatorDropMidOne.cancel();
                    valueAnimatorDropMidOne.setDuration(delayButtonMidOne[counterMidOne]);
                    valueAnimatorDropMidOne.start();

                } else {
                    ++counterMidOne;
                    valueAnimatorDropMidOne.cancel();
                    valueAnimatorDropMidOne.setDuration(delayButtonMidOne[counterMidOne]);
                    valueAnimatorDropMidOne.start();
                }

            } else if (v.getId() == buttonMidTwo.getId()) {

                if (finale.getY() + boundary >= buttonMidTwo.getY() && buttonMidTwo.getY() >= finale.getY() - boundary) {
                    score += scoreIncreaseRate;
                    txtScore.setText(String.format("Score: %d", score));
                    congratulatoryMessage.show();
                    ++counterMidTwo;
                    valueAnimatorDropMidTwo.cancel();
                    valueAnimatorDropMidTwo.setDuration(delayButtonMidTwo[counterMidTwo]);
                    valueAnimatorDropMidTwo.start();

                } else {
                    ++counterMidTwo;
                    valueAnimatorDropMidTwo.cancel();
                    valueAnimatorDropMidTwo.setDuration(delayButtonMidTwo[counterMidTwo]);
                    valueAnimatorDropMidTwo.start();
                }

            } else {

                if (finale.getY() + boundary >= buttonRight.getY() && buttonRight.getY() >= finale.getY() - boundary) {
                    score += scoreIncreaseRate;
                    txtScore.setText(String.format("Score: %d", score));
                    congratulatoryMessage.show();
                    ++counterRight;
                    valueAnimatorDropRight.cancel();
                    valueAnimatorDropRight.setDuration(delayButtonRight[counterRight]);
                    valueAnimatorDropRight.start();

                } else {
                    ++counterRight;
                    valueAnimatorDropRight.cancel();
                    valueAnimatorDropRight.setDuration(delayButtonRight[counterRight]);
                    valueAnimatorDropRight.start();
                }
            }
            return true;
        }
        else {
            return false;
        }
    }
}