package com.megvii.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.megvii.buz.utils.BitmapUtil;
import com.megvii.facepp.api.bean.HumanSegmentResponse;

/**
 * @author by licheng on 2018/9/27.
 */

public class BodySegmentResultView extends BaseResultView<HumanSegmentResponse> {

    private ImageView imageView;

    public BodySegmentResultView(Context context) {
        super(context);
    }

    public BodySegmentResultView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BodySegmentResultView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void refresh(HumanSegmentResponse data) {
        imageView.setImageBitmap(BitmapUtil.base64ToBitmap(data.getBody_image()));
    }

    @Override
    public View getSubView(Context context) {
        imageView = new ImageView(context);
        return imageView;
    }
}
