package com.braincollaboration.mathboom;

import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;

import com.braincollaboration.mathboom.utils.ViewUtils;

public abstract class ClickListenerWrapper implements OnClickListener {

    private static final int CLICK_DELAY = 1000;
    private volatile boolean actionPerforming;
    private View explosionField;

    public abstract void onClickWrapped(View v);

    public ClickListenerWrapper(View explosionField) {
        this.explosionField = explosionField;
    }

    @Override
    public synchronized void onClick(final View v) {
        if (actionPerforming) {
            return;
        }
        actionPerforming = true;
        onClickWrapped(v);
        ViewUtils.scaleView(v, 1, 0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                actionPerforming = false;
                explosionField.setScaleY(1);
                explosionField.setScaleX(1);
                ViewUtils.scaleView(v, 0, 1);
            }
        }, CLICK_DELAY);
    }
}