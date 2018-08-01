package com.megvii.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.megvii.buz.utils.BitmapUtil;
import com.megvii.facepp.api.bean.DriverLicenseResponse;
import com.megvii.facepp.api.bean.OcrIdCardResponse;
import com.megvii.ui.R;
import com.megvii.ui.presenter.OCRDriverPresenter;
import com.megvii.ui.presenter.OCRIDCardPresenter;
import com.megvii.ui.view.OCRDriverResultView;
import com.megvii.ui.view.OCRIDCardResultView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by licheng on 2018/7/13.
 */

public class OCRDriverFragment extends FaceActionFragment<OCRDriverPresenter> {

    private OCRDriverResultView attributeView;

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    @Override
    protected void doAction(Bitmap... bitmap) {
        super.doAction(bitmap);
        Map<String, String> params = new HashMap<>();
        params.put("return_score", "1");
        getP().doAction(params, BitmapUtil.toByteArray(bitmap[0]));
    }

    public void onSuccess(final DriverLicenseResponse response) {
        super.onSuccess();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                resultContainer.removeAllViews();
                attributeView = new OCRDriverResultView(context);
                attributeView.refresh(response);
                resultContainer.addView(attributeView);
            }
        });
    }

    @Override
    int sampleResId() {
        return R.drawable.demo_jiazhao;
    }

    @Override
    public OCRDriverPresenter newP() {
        return new OCRDriverPresenter();
    }
}
