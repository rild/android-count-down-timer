package rimp.rild.com.android.android_count_down_timer;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView mTimerText;
    private Button mStartButton;
    ProgressBar mProgressBarInterval;
    ProgressBar mProgressBarTotal;

    private CountDown mCountDown;
    private int mSetTimeMillis = 5000;
//    private int mIntervalDetail = 500;
    private int mInterval = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViews();

        mTimerText.setText(String.valueOf(mSetTimeMillis / 1000));

        mCountDown = new CountDown(mSetTimeMillis, mInterval);

        mCountDown.setOnFinishListener(new CountDown.OnFinishListener() {
            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "カウント終了", Toast.LENGTH_SHORT).show();
                mTimerText.setText(String.valueOf(0));
            }
        });

        mCountDown.setOnFinishListener(() -> {
            Toast.makeText(MainActivity.this, "カウント終了", Toast.LENGTH_SHORT).show();
            mTimerText.setText(String.valueOf(0));
        });


        mCountDown.setOnTickListener(new CountDown.OnTickListener() {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimerText.setText(String.valueOf(millisUntilFinished / 1000));
//                startProgressAnim();
//                startProgressTotalAnim();
            }
        });

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountDown.start();
            }
        });
    }

    private void getViews() {
        mTimerText = (TextView) findViewById(R.id.timer_text);
        mStartButton = (Button) findViewById(R.id.start_button);
        mProgressBarInterval = (ProgressBar) findViewById(R.id.progress_interval);
        mProgressBarTotal = (ProgressBar) findViewById(R.id.progress_total);
    }

    private void initCountDown() {
        mCountDown = new CountDown(mSetTimeMillis, 1000);
        mCountDown.setOnFinishListener(new CountDown.OnFinishListener() {
            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "カウント終了", Toast.LENGTH_SHORT).show();
                mTimerText.setText(String.valueOf(0));

                mProgressBarInterval.clearAnimation();
                mProgressBarTotal.clearAnimation();
            }
        });


        mCountDown.setOnTickListener(new CountDown.OnTickListener() {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimerText.setText(String.valueOf(millisUntilFinished / 1000));
                startProgressAnim();
                startProgressTotalAnim();
            }
        });
    }

    private void startProgressAnim() {
        // progress bar max: 500
        ObjectAnimator innerProgressAnim = ObjectAnimator.ofInt (mProgressBarInterval, "progress", 0, 500); // see this max value coming back here, we animale towards that value
        innerProgressAnim.setDuration (mInterval); //in milliseconds
        innerProgressAnim.setInterpolator (new DecelerateInterpolator());
        innerProgressAnim.start();
    }

    private void startProgressTotalAnim() {
        // progress bar max: 500
        ObjectAnimator outerProgressAnim = ObjectAnimator.ofInt (mProgressBarTotal, "progress_total", 0, 500); // see this max value coming back here, we animale towards that value
        outerProgressAnim.setDuration (mInterval); //in milliseconds
        outerProgressAnim.setInterpolator (new DecelerateInterpolator());
        outerProgressAnim.start();
    }
}
