package com.megvii.ui.view;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.megvii.facepp.api.bean.Beauty;

/**
 * @author by licheng on 2018/7/12.
 */

public class BeautyRatingView extends RatingView<Beauty> {

    public BeautyRatingView(Context context) {
        super(context);
    }

    public BeautyRatingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BeautyRatingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void refresh(Beauty data) {
        circlePercentBar.setPercentData(data.getFemale_score(), new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                return input;
            }
        });

        txtDesc.setText(data.getFemale_score() + "\n" + data.getMale_score());
    }
}
