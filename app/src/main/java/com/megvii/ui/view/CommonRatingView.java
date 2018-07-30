package com.megvii.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.Interpolator;

import com.megvii.ui.bean.RatingBean;

/**
 * @author by licheng on 2018/7/16.
 */

public class CommonRatingView extends RatingView<RatingBean> {

    public CommonRatingView(Context context) {
        super(context);
    }

    public CommonRatingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CommonRatingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void refresh(RatingBean data) {
        circlePercentBar.setPercentData(data.getScore(), new Interpolator() {
            @Override
            public float getInterpolation(float input) {
                return input;
            }
        });
        txtDesc.setText(data.getDesc());
    }
}
