package com.megvii.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.megvii.facepp.api.bean.Skinstatus;

/**
 * @author by licheng on 2018/7/12.
 */

public class SkinRatingResultView extends BaseResultView<Skinstatus> {

    private SkinRatingView skinRatingView;

    public SkinRatingResultView(Context context) {
        super(context);
    }

    public SkinRatingResultView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SkinRatingResultView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void refresh(Skinstatus data) {
        skinRatingView.refresh(data);
    }

    @Override
    public View getSubView(Context context) {
        skinRatingView = new SkinRatingView(context);
        return skinRatingView;
    }
}
