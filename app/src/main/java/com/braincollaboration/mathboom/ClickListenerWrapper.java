package com.braincollaboration.mathboom;

import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;

public abstract class ClickListenerWrapper implements OnClickListener {

    private static final int CLICK_DELAY = 600;

    private volatile boolean actionPerforming;

    public abstract void onClickWrapped(View v);

    @Override
    public synchronized void onClick(final View v) {
        if (actionPerforming) {
            return;
        }
        actionPerforming = true;
        onClickWrapped(v);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                actionPerforming = false;
            }
        }, CLICK_DELAY);
    }
}