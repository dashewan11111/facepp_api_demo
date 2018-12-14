package com.megvii.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.megvii.buz.utils.BitmapUtil;
import com.megvii.facepp.api.bean.HumanSegmentResponse;
import com.megvii.ui.R;
import com.megvii.ui.presenter.HeadSegmentPresenter;
import com.megvii.ui.view.HeadSegmentResultView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by licheng on 2018/7/30.
 */

public class HeadSegmentFragment extends FaceActionFragment<HeadSegmentPresenter> {

    private HeadSegmentResultView resultView;

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    @Override
    protected void doAction(Bitmap... bitmap) {
        super.doAction(bitmap);
        Map<String, String> params = new HashMap<>();
        getP().doAction(params, bitmap[0]);
    }

    @Override
    public void onSuccess(Object... response) {
        super.onSuccess(response);
        final HumanSegmentResponse humanSegmentResponse = (HumanSegmentResponse) response[0];
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                resultContainer.removeAllViews();
                resultView = new HeadSegmentResultView(getActivity());
                resultView.refresh(humanSegmentResponse);
                resultContainer.addView(resultView);
            }
        });

    }

    @Override
    public void onHeadCrop(List<Bitmap> cropFaceList) {
        getP().segment(new HashMap<String, String>(), BitmapUtil.toByteArray(cropFaceList.get(0)));
    }

    @Override
    public HeadSegmentPresenter newP() {
        return new HeadSegmentPresenter();
    }

    @Override
    int sampleResId() {
        return R.drawable.demo_body;
    }
}
