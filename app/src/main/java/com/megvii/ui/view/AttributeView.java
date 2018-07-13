package com.megvii.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

/**
 * @author by licheng on 2018/7/13.
 */

public abstract class AttributeView<D> extends LinearLayout implements IResultView<D> {

    protected Context context;

    public AttributeView(Context context) {
        super(context);
        init(context);
    }

    public AttributeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AttributeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        for (View view : subViews()) {
            addView(view);
        }
    }

    abstract List<View> subViews();
}
