package com.megvii.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.megvii.buz.utils.BitmapUtil;
import com.megvii.facepp.api.bean.DriverLicenseResponse;
import com.megvii.facepp.api.bean.Vehicle;
import com.megvii.ui.R;
import com.megvii.ui.presenter.OCRDriverPresenter;
import com.megvii.ui.presenter.OCRVehiclePresenter;
import com.megvii.ui.view.OCRDriverResultView;
import com.megvii.ui.view.OCRVehicleResultView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by licheng on 2018/7/13.
 */

public class OCRVehicleFragment extends FaceActionFragment<OCRVehiclePresenter> {

    private OCRVehicleResultView attributeView;

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    @Override
    protected void doAction(Bitmap... bitmap) {
        super.doAction(bitmap);
        getP().doAction(new HashMap<>(), BitmapUtil.toByteArray(bitmap[0]));
    }

    public void onSuccess(final Vehicle response) {
        super.onSuccess();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                resultContainer.removeAllViews();
                attributeView = new OCRVehicleResultView(context);
                attributeView.refresh(response);
                resultContainer.addView(attributeView);
            }
        });
    }

    @Override
    int sampleResId() {
        return R.drawable.demo_xingshizheng;
    }

    @Override
    public OCRVehiclePresenter newP() {
        return new OCRVehiclePresenter();
    }
}
