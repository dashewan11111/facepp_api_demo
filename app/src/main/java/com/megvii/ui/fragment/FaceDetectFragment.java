package com.megvii.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import com.megvii.buz.utils.BitmapUtil;
import com.megvii.facepp.api.bean.Face;
import com.megvii.ui.R;
import com.megvii.ui.presenter.FaceDetectPresenter;
import com.megvii.ui.view.FaceDetectAttributeView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by licheng on 2018/7/13.
 */

public class FaceDetectFragment extends FaceActionFragment<FaceDetectPresenter> {

    private FaceDetectAttributeView attributeView;

    private List<Face> faces;

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    @Override
    protected void doAction(Bitmap... bitmap) {
        super.doAction(bitmap);
        Map<String, String> params = new HashMap<>();
        params.put("return_attributes", "gender,age,smiling,headpose,facequality,blur,eyestatus,emotion,ethnicity,beauty,mouthstatus,eyegaze,skinstatus");
        params.put("return_landmark", "2");
        getP().doAction(params, BitmapUtil.toByteArray(bitmap[0]), bitmap[0]);
    }

    public void onSuccess(final List<Face> faces) {
        super.onSuccess();
        this.faces = faces;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                resultContainer.removeAllViews();
                attributeView = new FaceDetectAttributeView(context);
                attributeView.refresh(faces.get(0));
                resultContainer.addView(attributeView);
            }
        });
    }

    @Override
    protected void onCropFaceClicked(int index) {
        attributeView.refresh(faces.get(index));
    }

    @Override
    int sampleResId() {
        return R.drawable.six;
    }

    @Override
    public FaceDetectPresenter newP() {
        return new FaceDetectPresenter();
    }
}
