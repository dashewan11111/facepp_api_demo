package com.megvii.ui.view;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.megvii.facepp.api.bean.Skinstatus;

/**
 * @author by licheng on 2018/7/12.
 */

public class SkinRatingView extends RatingView<Skinstatus> {

    public SkinRatingView(Context context) {
        super(context);
    }

    public SkinRatingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SkinRatingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void refresh(Skinstatus data) {
        circlePercentBar.setPercentData(data.getHealth(), new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                return input;
            }
        });

        txtDesc.setText("健康指数");
    }
}
