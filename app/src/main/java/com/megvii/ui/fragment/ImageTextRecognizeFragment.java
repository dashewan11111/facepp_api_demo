package com.megvii.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.megvii.buz.utils.BitmapUtil;
import com.megvii.facepp.api.bean.BankCard;
import com.megvii.facepp.api.bean.TextResult;
import com.megvii.ui.R;
import com.megvii.ui.presenter.ImageLicensePlatePresenter;
import com.megvii.ui.presenter.ImageTextRecognizePresenter;
import com.megvii.ui.view.OCRBankCardResultView;
import com.megvii.ui.view.TextRecognizeResultView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by licheng on 2018/7/13.
 */

public class ImageTextRecognizeFragment extends FaceActionFragment<ImageTextRecognizePresenter> {

    private TextRecognizeResultView attributeView;

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    @Override
    protected void doAction(Bitmap... bitmap) {
        super.doAction(bitmap);
        getP().doAction(new HashMap<>(), BitmapUtil.toByteArray(bitmap[0]));
    }

    public void onSuccess(final TextResult result) {
        super.onSuccess();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                resultContainer.removeAllViews();
                attributeView = new TextRecognizeResultView(context);
                attributeView.refresh(result);
                resultContainer.addView(attributeView);
            }
        });
    }

    @Override
    int sampleResId() {
        return R.drawable.demo_test_recognize;
    }

    @Override
    public ImageTextRecognizePresenter newP() {
        return new ImageTextRecognizePresenter();
    }
}
