package com.megvii.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.megvii.buz.utils.BitmapUtil;
import com.megvii.facepp.api.bean.HumanBodyDetectResponse;
import com.megvii.facepp.api.bean.HumanSegmentResponse;
import com.megvii.ui.R;
import com.megvii.ui.presenter.BodyDetectPresenter;
import com.megvii.ui.presenter.BodySegmentPresenter;
import com.megvii.ui.view.BodySegmentResultView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by licheng on 2018/7/30.
 */

public class BodySegmentFragment extends FaceActionFragment<BodySegmentPresenter> {

    private BodySegmentResultView resultView;

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    @Override
    protected void doAction(Bitmap... bitmap) {
        super.doAction(bitmap);
        Map<String, String> params = new HashMap<>();
        getP().doAction(params, BitmapUtil.toByteArray(bitmap[0]));
    }

    @Override
    public void onSuccess(Object... response) {
        super.onSuccess(response);
        final HumanSegmentResponse humanSegmentResponse = (HumanSegmentResponse) response[0];
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                resultContainer.removeAllViews();
                resultView = new BodySegmentResultView(getActivity());
                resultView.refresh(humanSegmentResponse);
                resultContainer.addView(resultView);
            }
        });

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
