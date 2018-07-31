package com.megvii.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.megvii.facepp.api.bean.Gesture;
import com.megvii.ui.R;
import com.megvii.ui.datasource.GestureDataSource;

/**
 * @author by licheng on 2018/7/31.
 */

public class GestureResultView extends LinearLayout implements IResultView<Gesture> {

    private TextView txtName;
    private TextView txtConfidence;

    private ImageView imageDisplay;

    public GestureResultView(Context context) {
        super(context);
        init(context);
    }

    public GestureResultView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GestureResultView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.view_gesture, this);
        txtName = rootView.findViewById(R.id.name);
        txtConfidence = rootView.findViewById(R.id.confidence);
        imageDisplay = rootView.findViewById(R.id.display_image);
    }

    @Override
    public void refresh(Gesture gesture) {
        try {
            GestureDataSource.GestureResult result = GestureDataSource.getGestureResult(gesture);
            if (null != result) {
                txtName.setText(result.getDisplayName());
                txtConfidence.setText(getResources().getString(R.string.confidence_x, result.getConfidence(), "%"));
                imageDisplay.setImageResource(result.getDisplayResId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
