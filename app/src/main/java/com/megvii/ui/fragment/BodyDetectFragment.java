package com.megvii.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.megvii.buz.utils.BitmapUtil;
import com.megvii.facepp.api.bean.HumanBody;
import com.megvii.facepp.api.bean.HumanBodyDetectResponse;
import com.megvii.ui.R;
import com.megvii.ui.presenter.BodyDetectPresenter;
import com.megvii.ui.view.BodyDetectAttributeView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by licheng on 2018/7/30.
 */

public class BodyDetectFragment extends FaceActionFragment<BodyDetectPresenter> {

    private BodyDetectAttributeView attributeView;

    private List<HumanBody> humanBodies;

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    @Override
    protected void doAction(Bitmap... bitmap) {
        super.doAction(bitmap);
        Map<String, String> params = new HashMap<>();
        params.put("return_attributes", "gender,upper_body_cloth,lower_body_cloth");
        getP().doAction(params, BitmapUtil.toByteArray(bitmap[0]), bitmap[0]);
    }

    @Override
    public void onSuccess(Object... response) {
        super.onSuccess(response);
        HumanBodyDetectResponse response1 = (HumanBodyDetectResponse) response[0];
        humanBodies = response1.getHumanbodies();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                resultContainer.removeAllViews();
                attributeView = new BodyDetectAttributeView(context);
                attributeView.refresh(humanBodies.get(0).getAttributes());
                resultContainer.addView(attributeView);
            }
        });
    }

    @Override
    protected void onCropFaceClicked(int index) {
        attributeView.refresh(humanBodies.get(index).getAttributes());
    }

    @Override
    public BodyDetectPresenter newP() {
        return new BodyDetectPresenter();
    }

    @Override
    int sampleResId() {
        return R.drawable.demo_multi_body;
    }
}
