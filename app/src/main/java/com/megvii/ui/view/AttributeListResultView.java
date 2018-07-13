package com.megvii.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.megvii.facepp.api.bean.Face;

/**
 * @author by licheng on 2018/7/13.
 */

public class AttributeListResultView extends BaseResultView<Face> {

    private FaceDetectAttributeView attributeListView;

    public AttributeListResultView(Context context) {
        super(context);
    }

    public AttributeListResultView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AttributeListResultView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void refresh(Face data) {
        attributeListView.refresh(data);
    }

    @Override
    public View getSubView(Context context) {
        attributeListView = new FaceDetectAttributeView(context);
        return attributeListView;
    }
}
