package com.megvii.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.megvii.facepp.api.bean.BankCard;
import com.megvii.facepp.api.bean.SceneDetectResponse;
import com.megvii.ui.R;

/**
 * @author by licheng on 2018/7/31.
 */

public class ImageSceneResultView extends LinearLayout implements IResultView<SceneDetectResponse> {

    private TextView txtScene;
    private TextView txtObject;

    public ImageSceneResultView(Context context) {
        super(context);
        init(context);
    }

    public ImageSceneResultView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ImageSceneResultView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.view_scene, this);
        txtScene = rootView.findViewById(R.id.scene);
        txtObject = rootView.findViewById(R.id.objects);
    }

    @Override
    public void refresh(SceneDetectResponse sceneDetectResponse) {
        if (null != sceneDetectResponse) {
            txtScene.setText(sceneDetectResponse.getScenes().toString());
            txtObject.setText(sceneDetectResponse.getObjects().toString());
        }
    }
}
