package com.plan.ahead.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.plan.ahead.R;

/**
 * Created by mihai on 27.02.2018.
 */

public class TimetableView extends View {
    public boolean showText;
    public Integer textPos;
    public Paint paint;
    Rect r;

    public TimetableView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.TimetableView,
                0, 0);

        try {
            showText = a.getBoolean(R.styleable.TimetableView_showText, false);
            textPos = a.getInteger(R.styleable.TimetableView_labelPosition, 0);
        } finally {
            a.recycle();
        }
    }

    public boolean isShowText() {
        return this.showText;
    }

    public void setShowText(boolean showText) {
        this.showText = showText;
        invalidate();
        requestLayout();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        r = new Rect(0, 0, 200, 200);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.init();

        // Draw the pointer


        canvas.drawRect(r, paint);
    }
}
