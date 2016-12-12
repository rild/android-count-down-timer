package rimp.rild.com.android.android_count_down_timer;

import android.os.CountDownTimer;

/**
 * Created by rild on 16/09/21.
 */
public class CountDown extends CountDownTimer {
    OnFinishListener onFinishListener;
    OnTickListener onTickListener;

    public CountDown(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onFinish() {
        //OnFinishListenerの "onFinish" メソッドが呼ばれる
        if (onFinishListener != null) onFinishListener.onFinish();
    }

    @Override
    public void onTick(long millisUntilFinished) {
        //OnTickListenerの "onFinish" メソッドが呼ばれる
        if (onTickListener != null) onTickListener.onTick(millisUntilFinished);
    }

    public void setOnFinishListener(OnFinishListener listener) {
        this.onFinishListener = listener;
    }

    public void setOnTickListener(OnTickListener listener) {
        this.onTickListener = listener;
    }

    //リスナー
    interface OnFinishListener {
        void onFinish();
    }

    interface OnTickListener {
        void onTick(long millisUntilFinished);
    }
}
