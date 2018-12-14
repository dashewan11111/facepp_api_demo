package com.megvii.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.megvii.buz.utils.BitmapUtil;
import com.megvii.facepp.api.bean.HumanSegmentResponse;
import com.megvii.ui.R;

/**
 * @author by licheng on 2018/9/27.
 */

public class HeadSegmentResultView extends BaseResultView<HumanSegmentResponse> {

    private ImageView imageSource;

    private Bitmap person;

    public HeadSegmentResultView(Context context) {
        super(context);
    }

    public HeadSegmentResultView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HeadSegmentResultView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void refresh(HumanSegmentResponse data) {
        person = BitmapUtil.base64ToBitmap(data.getBody_image());
        imageSource.setImageBitmap(person);
    }

    @Override
    public View getSubView(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.view_head_segment, null);
        imageSource = rootView.findViewById(R.id.result);
        return rootView;
    }
}
