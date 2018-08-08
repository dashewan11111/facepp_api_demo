package com.megvii.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.megvii.buz.utils.BitmapUtil;
import com.megvii.facepp.api.bean.SceneDetectResponse;
import com.megvii.ui.R;
import com.megvii.ui.presenter.ImageScenePresenter;
import com.megvii.ui.view.ImageSceneResultView;

import java.util.HashMap;

/**
 * @author by licheng on 2018/7/13.
 */

public class ImageSceneFragment extends FaceActionFragment<ImageScenePresenter> {

    private ImageSceneResultView attributeView;

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    @Override
    protected void doAction(Bitmap... bitmap) {
        super.doAction(bitmap);
        getP().doAction(new HashMap<>(), BitmapUtil.toByteArray(bitmap[0]));
    }

    public void onSuccess(final SceneDetectResponse response) {
        super.onSuccess();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                resultContainer.removeAllViews();
                attributeView = new ImageSceneResultView(context);
                attributeView.refresh(response);
                resultContainer.addView(attributeView);
            }
        });
    }

    @Override
    int sampleResId() {
        return R.drawable.demo_ski;
    }

    @Override
    public ImageScenePresenter newP() {
        return new ImageScenePresenter();
    }
}
