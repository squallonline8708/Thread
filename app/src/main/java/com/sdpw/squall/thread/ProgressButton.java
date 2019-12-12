package com.sdpw.squall.thread;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.Button;

public class ProgressButton extends Button {

    private float progress;

    private Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);

    public ProgressButton(Context context) {
        super(context);
    }

    public ProgressButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ProgressButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float left= 0;
        float right=getWidth()*progress;
        float top=0;
        float bottom=getBottom();
        paint.setColor(Color.GREEN);
        //绘制矩形
        canvas.drawRect(left, top, right, bottom, paint);
        super.onDraw(canvas);
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

}
