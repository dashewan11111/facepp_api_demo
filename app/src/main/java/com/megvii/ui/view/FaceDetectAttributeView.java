package com.megvii.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.megvii.facepp.api.bean.Face;
import com.megvii.facepp.api.bean.FaceAttributes;
import com.megvii.ui.R;

/**
 * @author by licheng on 2018/7/13.
 */

public class FaceDetectAttributeView extends LinearLayout implements IResultView<Face> {

    TextView attributeAge;
    TextView attributeGender;
    TextView attributeMotion;
    TextView attributeHead;
    TextView attributeMouth;
    TextView attributeLeftEye;
    TextView attributeRightEye;

    private LinearLayout rootView;

    public FaceDetectAttributeView(Context context) {
        super(context);
        init(context);
    }

    public FaceDetectAttributeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FaceDetectAttributeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        rootView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.view_face_attribute, this);
        attributeAge = rootView.findViewById(R.id.attribute_age);
        attributeGender = rootView.findViewById(R.id.attribute_gender);
        attributeMotion = rootView.findViewById(R.id.attribute_emotion);
        attributeHead = rootView.findViewById(R.id.attribute_head);
        attributeMouth = rootView.findViewById(R.id.attribute_mouth);
        attributeLeftEye = rootView.findViewById(R.id.attribute_left_eye);
        attributeRightEye = rootView.findViewById(R.id.attribute_right_eye);
    }

    @Override
    public void refresh(Face data) {
        FaceAttributes attributes = data.getAttributes();
        if (null == attributes) {
            return;
        }
        rootView.setVisibility(VISIBLE);
        attributeAge.setText(null == attributes.getAge() ? "" : attributes.getAge().getValue());
        attributeGender.setText(null == attributes.getGender() ? "" : attributes.getGender().getValue());
        attributeMotion.setText(null == attributes.getEmotion() ? "" : attributes.getEmotion().toString());
        attributeHead.setText(null == attributes.getHeadpose() ? "" : attributes.getHeadpose().toString());
        attributeMouth.setText(null == attributes.getMouthstatus() ? "" : attributes.getMouthstatus().toString());
        attributeLeftEye.setText(null == attributes.getEyestatus() ? "" : attributes.getEyestatus().getLeft_eye_status().toString());
        attributeRightEye.setText(null == attributes.getEyestatus() ? "" : attributes.getEyestatus().getRight_eye_status().toString());
        invalidate();
    }
}
