package rimp.rild.com.android.android_count_down_timer;

import android.os.CountDownTimer;

/**
 * Created by rild on 16/09/21.
 */
public class CountDown extends CountDownTimer {
    OnFinishListener finishListener;
    OnTickListener tickListener;

    public CountDown(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onFinish() {
        //OnFinishListenerの "onFinish" メソッドが呼ばれる
        finishListener.onFinish();
    }

    @Override
    public void onTick(long millisUntilFinished) {
        //OnTickListenerの "onFinish" メソッドが呼ばれる
        tickListener.onTick(millisUntilFinished);
    }

    //リスナー
    interface OnFinishListener {
        void onFinish();
    }

    interface OnTickListener {
        void onTick(long millisUntilFinished);
    }
}
