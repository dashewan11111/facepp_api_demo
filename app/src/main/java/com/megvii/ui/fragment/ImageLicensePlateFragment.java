package com.megvii.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.megvii.buz.utils.BitmapUtil;
import com.megvii.facepp.api.bean.BankCard;
import com.megvii.facepp.api.bean.LicensePlatResponse;
import com.megvii.ui.R;
import com.megvii.ui.presenter.ImageLicensePlatePresenter;
import com.megvii.ui.presenter.OCRBankCardPresenter;
import com.megvii.ui.view.LicensePlateResultView;
import com.megvii.ui.view.OCRBankCardResultView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by licheng on 2018/7/13.
 */

public class ImageLicensePlateFragment extends FaceActionFragment<ImageLicensePlatePresenter> {

    private LicensePlateResultView attributeView;

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    @Override
    protected void doAction(Bitmap... bitmap) {
        super.doAction(bitmap);
        getP().doAction(new HashMap<>(), BitmapUtil.toByteArray(bitmap[0]));
    }

    public void onSuccess(final LicensePlatResponse response) {
        super.onSuccess();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                resultContainer.removeAllViews();
                attributeView = new LicensePlateResultView(context);
                attributeView.refresh(response);
                resultContainer.addView(attributeView);
            }
        });
    }

    @Override
    int sampleResId() {
        return R.drawable.demo_car;
    }

    @Override
    public ImageLicensePlatePresenter newP() {
        return new ImageLicensePlatePresenter();
    }
}
