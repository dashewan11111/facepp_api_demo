package com.megvii.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.megvii.buz.utils.BitmapUtil;
import com.megvii.ui.R;
import com.megvii.ui.presenter.BodyDetectPresenter;
import com.megvii.ui.presenter.BodySegmentPresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by licheng on 2018/7/30.
 */

public class BodySegmentFragment extends FaceActionFragment<BodySegmentPresenter> {

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    @Override
    protected void doAction(Bitmap... bitmap) {
        super.doAction(bitmap);
        Map<String, String> params = new HashMap<>();
        params.put("return_attributes", "gender,upper_body_cloth,lower_body_cloth");
        getP().doAction(params, BitmapUtil.toByteArray(bitmap[0]));
    }

    @Override
    public void onSuccess(Object... response) {
        super.onSuccess(response);
    }

    @Override
    public BodySegmentPresenter newP() {
        return new BodySegmentPresenter();
    }

    @Override
    int sampleResId() {
        return R.drawable.demo_body;
    }
}
