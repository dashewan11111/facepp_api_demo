package com.megvii.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.megvii.facepp.api.bean.Beauty;

/**
 * @author by licheng on 2018/7/12.
 */

public class BeautyRatingResultView extends BaseResultView<Beauty> {

    private BeautyRatingView beautyRatingView;

    public BeautyRatingResultView(Context context) {
        super(context);
    }

    public BeautyRatingResultView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BeautyRatingResultView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void refresh(Beauty data) {
        beautyRatingView.refresh(data);
    }

    @Override
    public View getSubView(Context context) {
        beautyRatingView = new BeautyRatingView(context);
        return beautyRatingView;
    }
}
