package com.weds.lordbond.custom_view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class LordBondTextView extends AppCompatTextView {
    public LordBondTextView(Context context) {
        super(context);
        init(context);
    }

    public LordBondTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LordBondTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
        setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Calibri Regular.ttf"));
    }
}
