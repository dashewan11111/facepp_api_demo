package com.megvii.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tu.circlelibrary.CirclePercentBar;
import com.megvii.ui.R;

/**
 * @author by licheng on 2018/7/12.
 */

public abstract class RatingView<D> extends LinearLayout implements IResultView<D> {

    protected CirclePercentBar circlePercentBar;

    protected TextView txtDesc;

    public RatingView(Context context) {
        super(context);
        init(context);
    }

    public RatingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RatingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.view_rating, this);

        circlePercentBar = rootView.findViewById(R.id.circle_bar);
        txtDesc = rootView.findViewById(R.id.textDesc);
    }
}
