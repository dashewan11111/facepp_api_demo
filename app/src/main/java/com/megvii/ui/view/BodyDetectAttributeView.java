package com.megvii.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.megvii.facepp.api.bean.BodyAttribute;
import com.megvii.ui.R;

/**
 * @author by licheng on 2018/7/13.
 */

public class BodyDetectAttributeView extends LinearLayout implements IResultView<BodyAttribute> {

    TextView attributeUpCloth;
    TextView attributeGender;
    TextView attributeDownCloth;

    private LinearLayout rootView;

    public BodyDetectAttributeView(Context context) {
        super(context);
        init(context);
    }

    public BodyDetectAttributeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BodyDetectAttributeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        rootView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.view_body_attribute, this);
        attributeGender = rootView.findViewById(R.id.attribute_gender);
        attributeUpCloth = rootView.findViewById(R.id.attribute_up_body_cloth);
        attributeDownCloth = rootView.findViewById(R.id.attribute_down_body_cloth);
    }

    @Override
    public void refresh(BodyAttribute attributes) {
        if (null == attributes) {
            return;
        }
        rootView.setVisibility(VISIBLE);
        attributeGender.setText(null == attributes.getGender() ? "" : attributes.getGender().toString());
        attributeUpCloth.setText(null == attributes.getUpper_body_cloth() ? "" : attributes.getUpper_body_cloth().getUpper_body_cloth_color());
        attributeDownCloth.setText(null == attributes.getLower_body_cloth() ? "" : attributes.getLower_body_cloth().getLower_body_cloth_color());
        invalidate();
    }
}
